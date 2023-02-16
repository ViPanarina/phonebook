package api.tests.contact;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.contact.UpdateContactDTO;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateContactTest extends ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;
    UpdateContactDTO updateContactDto;
    Response response;
    int id;

    @BeforeMethod(groups = ("positive"))
    public void precondition() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
    }

    @AfterMethod(groups = ("positive"))
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test
    public void updateContactTest() {
        UpdateContactDTO updateContactDTO = new UpdateContactDTO();

        updateContactDto.setId(id);
        updateContactDto.setFirstName(faker.name().firstName());
        updateContactDto.setLastName(faker.name().lastName());
        updateContactDto.setDescription(faker.lorem().sentence(4));
        response = doPutRequest(EndPoint.UPDATE_CONTACT, 200, updateContactDto);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
        Assert.assertEquals(response.jsonPath().getString("firstName"), updateContactDto.getFirstName());
        Assert.assertEquals(response.jsonPath().getString("lastName"), updateContactDto.getLastName());
        Assert.assertEquals(response.jsonPath().getString("description"), updateContactDto.getDescription());
    }
}
