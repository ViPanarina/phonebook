package e2e.helpers;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ContactHelpers extends CommonHelpers {
    public String expectedErrMsgWithVoid = "Contact save fail";
    By contactButton = By.cssSelector("[href=\"/contacts\"]");
    By dialogWindow = By.xpath("//*[@role='dialog']");
    By newContactFirstName = By.id("form-name");
    By newContactLastName = By.id("form-lastName");
    By newContactDescription = By.xpath("//*[@role='dialog']//*[@placeholder='About']");
    By saveNewContactButton = By.xpath("//form//button[@type=\"submit\"]");
    By assertSaveDialogWindow = By.xpath("//*[@role='dialog']");
    public By contactsList = By.xpath("//div[@id='contacts-list']");
    By filledContactFirstName = By.id("contact-first-name");
    By filledContactLastName = By.id("contact-last-name");
    By filledContactDescription = By.id("contact-description");
    public By searchInput = By.xpath("//*[@placeholder='Search...']");
    By messageBlock = By.xpath("//div[@class='toast-body']");
    By homePage = By.xpath("//a[@class='navbar-brand']//*[name()='svg']");
    By searchWindow = By.xpath("//input[@id='input-search-contact']");
    By groupList = By.xpath("//div[@id='contacts-list']//div[@class='list-group']");
    By groupListTap = By.xpath("//div[@id='contacts-list']//button[1]");
    By editContact = By.id("btn-edit-contact");
    By descriptionWindow = By.xpath("//textarea[@name='input-ec-description']");
    By lastNameWindow = By.xpath("//input[@name='input-ec-lastName']");
    By saveEditContact = By.xpath("//button[normalize-space()='Save']");
    Faker faker = new Faker();
    public By foundContact = By.xpath("//div[@class=\"list-group\"]/..//button[@ng-reflect-router-link=\"/contacts/1726\"]/..//b");
    public By contactInfoWindow = By.xpath("//div[@class=\"mt-1 tab-content\"]");
    public By buttonEdit = By.xpath("//button[@id=\"btn-edit-contact\"]");
    By editLastNameField = By.name("input-ec-lastName");
    By editDescriptionField = By.name("input-ec-description");
    public By buttonSubmitEdition = By.xpath("//div[@class=\"col-sm-3\"]/..//button[@type=\"submit\"]");


    public void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        //Filler by creation name
        fillField(firstName, searchInput);
    }


    public void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(groupListTap).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }

    public void checkFieldsOnContactInfo(String firstName, String lastName, String description) {
        checkItemText(filledContactFirstName, firstName, "Actual first name is not equal expected first name");
        checkItemText(filledContactLastName, lastName, "Actual last name is not equal expected last name");
        checkItemText(filledContactDescription, description, "Actual description name is not equal expected description");
    }

    public void openContact() {
        clickOnVisibleElement(groupList);
    }

    public void fillAddContactFieldWithVoid(String voidFirstName, String lastName, String description) {
        fillField(voidFirstName, newContactFirstName);
        fillField(lastName, newContactLastName);
        fillField(description, newContactDescription);
    }

    public void saveInvalidContactWithVoidFirstName() {
        driver.findElement(saveNewContactButton).click();
    }

    public void checkErrorMessage(By locator, String expectedErrMsgWithVoid) {
        String err = "Actual error message isn't equal expected ";
        checkItemText(By.id("pop-up-error-add-contact"), expectedErrMsgWithVoid, err);
    }

    public ContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void changeLanguage() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();
    }

}
