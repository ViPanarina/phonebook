package api.tests.contact;

import api.enums.EndPoint;
import api.model.ContactDto;
import api.tests.ApiBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class DeleteContactTest extends ApiBase {
    int id;
    int wrongId;
    Response response;
    ContactDto contactDto;


    @BeforeMethod
    public void precondition() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");

    }

    @Test(groups = ("positive"))
    public void deleteContactTest() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test(groups = ("negative"))
    public void deleteContactWithoutId() {
        Random rnd = new Random();
        wrongId = 100000 + rnd.nextInt(900000);
        response = doDeleteRequest(EndPoint.DELETE_CONTACT, 500, wrongId);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE);
    }


}
