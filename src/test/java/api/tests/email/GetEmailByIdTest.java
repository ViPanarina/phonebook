package api.tests.email;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.email.AddEmailDto;
import api.model.email.EmailDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetEmailByIdTest extends ApiBase {
    Faker faker = new Faker();
    int contactId;
    int wrongId;
    Response response;
    String email = faker.internet().emailAddress();
    ContactDto contactDto;
    AddEmailDto addEmailDto;
    int emailId;
    Response responseForEmail;

    @BeforeMethod(groups = {"positive", "correctId"})
    public void precondition() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        contactId = response.jsonPath().getInt("id");
        addEmailDto = new AddEmailDto();
        addEmailDto.setEmail(email);
        addEmailDto.setContactId(contactId);
        doPostRequest(EndPoint.ADD_NEW_EMAIL, 201, addEmailDto);
        responseForEmail = doGetRequestWithParam(EndPoint.GET_CONTACT_BY_CONTACT_ID, 200, contactId);
        emailId = responseForEmail.jsonPath().getInt("[0].id");
    }

    @AfterMethod(groups = {"positive", "correctId"})
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, contactId);
    }

    @Test
    public void getEmailByIdTest() {
        response = doGetRequestWithParam(EndPoint.GET_EMAIL_BY_EMAIL_ID, 200, emailId);
        EmailDto emailDto = response.as(EmailDto.class);
        Assert.assertEquals(emailDto.getId(), emailId);
        Assert.assertEquals(emailDto.getEmail(), email);
        Assert.assertEquals(emailDto.getContactId(), contactId);
    }
}
