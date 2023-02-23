package api.tests.phone;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.phone.PhoneDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AddNewPhoneTest extends ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;
    Response response;
    PhoneDto phoneDto;
    int id;

    @BeforeMethod
    public void precondition() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
    }

    @AfterMethod
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }


    @Test
    public void addNewPhoneNumber() {
        phoneDto = new PhoneDto();
        phoneDto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        phoneDto.setContactId(id);
        phoneDto.setCountryCode("+" + faker.phoneNumber().subscriberNumber(2));
        doPostRequest(EndPoint.ADD_NEW_PHONE, 201, phoneDto);
    }
}
