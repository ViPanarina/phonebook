package e2e.helpers;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ContactHelpers extends CommonHelpers {
    public String expectedErrMsgWithVoid = "Contact save fail";
    By newContactFirstName = By.id("form-name");
    By newContactLastName = By.id("form-lastName");
    By newContactDescription = By.xpath("//*[@role='dialog']//*[@placeholder='About']");
    By saveNewContactButton = By.xpath("//form//button[@type=\"submit\"]");
    By filledContactFirstName = By.id("contact-first-name");
    By filledContactLastName = By.id("contact-last-name");
    By filledContactDescription = By.id("contact-description");
    public By searchInput = By.xpath("//*[@placeholder='Search...']");
    By groupList = By.xpath("//div[@id='contacts-list']//div[@class='list-group']");
    By groupListTap = By.xpath("//div[@id='contacts-list']//button[1]");
    By editContact = By.id("btn-edit-contact");
    Faker faker = new Faker();
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

    public void openRemoveContactDialog() {
        openDialog((By.xpath("//*[@id='contacts-list']//*[@class='list-group-item']/img")));
    }

    public void removeContact() {
        clickOnVisibleElement(By.id("check-box-remove-contact"));
        clickOnVisibleElement(By.id("submit-remove"));
        setWait().until(ExpectedConditions.invisibilityOfElementLocated  // waiting 10 sec
                (By.xpath("//*[@role='dialog']")));
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
