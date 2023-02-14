package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateContactTest extends TestBase {

    Faker faker = new Faker();

    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void createNewContact(String firstName, String lastName, String description) throws InterruptedException {
        // for (int i = 0; i < 3; i++) {
        //String firstName = faker.internet().uuid();
        //String lastName = faker.internet().uuid();
        //String description = faker.internet().uuid();
        Number expectedCountRow = 1;
        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        String firstAndLastName = firstName + lastName;
        //click on the button "Add new contact"
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        //Click on the button "Save"
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().goToContactPageAndFillFilterField(firstName);
        app.getCreateContact().checkCountRows(expectedCountRow);
        //}
        //Expected result: Created contact show with correct data in the contact table
    }

    @Test(dataProvider = "newContactWithCSV", dataProviderClass = DataProviders.class)
    public void createNewContactDataProviderWithFileCSV(String firstName, String lastName, String description) throws InterruptedException {
        Number expectedCountRow = 1;
        String firstAndLastName = firstName + lastName;
        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().goToContactPageAndFillFilterField(firstName);
        app.getCreateContact().checkCountRows(expectedCountRow);
    }

    //negative tests
    @Test
    public void createUserWithInvalidSymbol() {
        String voidFirstName = " ";
        String lastName = faker.internet().uuid();
        String description = faker.internet().uuid();
        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddContactFieldWithVoid(voidFirstName, lastName, description);
        app.getCreateContact().saveInvalidContactWithVoidFirstName();
        app.getCreateContact().checkErrorMessage(By.id("pop-up-error-add-contact"), app.getContact().expectedErrMsgWithVoid);
    }
}
