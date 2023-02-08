import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateContactTest extends ChangeLanguage {

    Faker faker = new Faker();

    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Viktoriia1", "Panarina1", "Berlin1"});
        list.add(new Object[]{"Viktoriia2", "Panarina2", "Berlin2"});
        list.add(new Object[]{"Viktoriia3", "Panarina3", "Berlin3"});
        return list.iterator();
    }

    ;

    String expectedErrMsgWithVoid = "Contact save fail";
    By contactButton = By.cssSelector("[href=\"/contacts\"]");
    By dialogWindow = By.xpath("//*[@role='dialog']");
    By newContactFirstName = By.id("form-name");
    By newContactLastName = By.id("form-lastName");
    By newContactDescription = By.xpath("//*[@role='dialog']//*[@placeholder='About']");
    By saveNewContactButton = By.xpath("//form//button[@type=\"submit\"]");
    By assertSaveDialogWindow = By.xpath("//*[@role='dialog']");
    By contactsList = By.xpath("//div[@id='contacts-list']");
    By filledContactFirstName = By.id("contact-first-name");
    By filledContactLastName = By.id("contact-last-name");
    By filledContactDescription = By.id("contact-description");
    By searchInput = By.xpath("//*[@placeholder='Search...']");

    private void openAddContactDialog() {
        driver.findElement(contactButton).click();
        Assert.assertTrue(isElementPresent(dialogWindow));

    }

    private void fillAddNewContactForm(String firstName, String lastName, String description) {
        //Fill field First name
        fillField(firstName, newContactFirstName);
        //Fill field Last name
        fillField(lastName, newContactLastName);
        //Fill field About
        fillField(description, newContactDescription);
    }

    private void saveNewContact() throws InterruptedException {
        driver.findElement(saveNewContactButton).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(assertSaveDialogWindow));
    }

    private void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(contactsList).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }

    private void checkFieldsOnContactInfoAfterCreatedContact(String firstName, String lastName, String description) {
        checkItemText(filledContactFirstName, firstName, "Actual first name is not equal expected first name");
        checkItemText(filledContactLastName, lastName, "Actual last name is not equal expected last name");
        checkItemText(filledContactDescription, description, "Actual description name is not equal expected description");
    }

    private void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        //Filler by creation name
        fillField(firstName, searchInput);
    }

    private void fillAddContactFieldWithVoid(String voidFirstName, String lastName, String description) {
        fillField(voidFirstName, newContactFirstName);
        fillField(lastName, newContactLastName);
        fillField(description, newContactDescription);
    }

    ;

    private void saveInvalidContactWithVoidFirstName() {
        driver.findElement(saveNewContactButton).click();
    }

    private void checkErrorMessage(By locator, String expectedErrMsgWithVoid) {
        String err = "Actual error message isn't equal expected ";
        checkItemText(By.id("pop-up-error-add-contact"), expectedErrMsgWithVoid, err);
    }
    
    @Test(dataProvider = "newContact")
    public void createNewContact(String firstName, String lastName, String description) throws InterruptedException {
        // for (int i = 0; i < 3; i++) {
        //String firstName = faker.internet().uuid();
        //String lastName = faker.internet().uuid();
        //String description = faker.internet().uuid();
        Number expectedCountRow = 1;

        String firstAndLastName = firstName + lastName;
        //click on the button "Add new contact"
        openAddContactDialog();
        fillAddNewContactForm(firstName, lastName, description);
        //Click on the button "Save"
        saveNewContact();
        checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        goToContactPageAndFillFilterField(firstName);
        checkCountRows(expectedCountRow);
        //}
        //Expected result: Created contact show with correct data in the contact table
    }

    //negative tests
    @Test
    public void createUserWithInvalidSymbol() {
        String voidFirstName = " ";
        String lastName = faker.internet().uuid();
        String description = faker.internet().uuid();
        openAddContactDialog();
        fillAddContactFieldWithVoid(voidFirstName, lastName, description);
        saveInvalidContactWithVoidFirstName();
        checkErrorMessage(By.id("pop-up-error-add-contact"), expectedErrMsgWithVoid);
    }
}
