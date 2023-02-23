package api.tests.phone;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.phone.PhoneDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetPhoneTest extends ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;
    Response response;
    PhoneDto phoneDto;
    int id;
    Response responseForPhone;
    int phoneId;


    @BeforeMethod
    public void precondition() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");

        phoneDto = new PhoneDto();
        phoneDto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        phoneDto.setContactId(id);
        phoneDto.setCountryCode("+" + faker.phoneNumber().subscriberNumber(2));
        doPostRequest(EndPoint.ADD_NEW_PHONE, 201, phoneDto);

        responseForPhone = doGetRequestWithParam(EndPoint.GET_LIST_OF_PHONES_BY_CONTACT_ID, 200, id);
        phoneId = responseForPhone.jsonPath().getInt("[0].id");
    }

    @AfterMethod
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    //bag
    @Test
    public void getPhoneByIdTest() {
        response = doGetRequestWithParam(EndPoint.GET_PHONE_BY_ID, 200, phoneId);

        Assert.assertEquals(response.jsonPath().getInt("id"), phoneId);
        Assert.assertEquals(response.jsonPath().getString("countryCode"), phoneDto.getCountryCode());
        Assert.assertEquals(response.jsonPath().getString("phoneNumber"), phoneDto.getPhoneNumber());
        Assert.assertEquals(response.jsonPath().getInt("contactId"), phoneDto.getContactId());
    }

    @Test
    public void getListOfPhonesByContactId() {
        Assert.assertEquals(responseForPhone.jsonPath().getInt("[0].id"), phoneId);
        Assert.assertEquals(responseForPhone.jsonPath().getString("[0].phoneNumber"), phoneDto.getPhoneNumber());
        Assert.assertEquals(responseForPhone.jsonPath().getString("[0].countryCode"), phoneDto.getCountryCode());
        Assert.assertEquals(responseForPhone.jsonPath().getInt("[0].contactId"), id);
    }

}
