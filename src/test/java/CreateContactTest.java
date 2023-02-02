import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTest extends ChangeLanguage {

    Faker faker = new Faker();

    private void openAddContactDialog() {
        driver.findElement(By.cssSelector("[href=\"/contacts\"]")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    private void fillAddNewContactForm(String firstName, String lastName, String description) {
        //Fill field First name
        fillField(firstName, By.id("form-name"));
        //Fill field Last name
        fillField(lastName, By.id("form-lastName"));
        //Fill field About
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));
    }

    private void saveNewContact() throws InterruptedException {
        driver.findElement(By.xpath("//form//button[@type=\"submit\"]")).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    private void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(By.xpath("//div[@id='contacts-list']")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }

    private void checkFieledsOnContactInfoAfterCreatedContact(String firstName, String lastName, String description) {
        checkItemText(By.id("contact-first-name"), firstName, "Actual first name is not equal expected first name");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name is not equal expected last name");
        checkItemText(By.id("contact-description"), description, "Actual description name is not equal expected description");
    }

    @Test
    public void createNewContact() throws InterruptedException {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.internet().uuid();
        Number expectedCountRow = 1;

        String firstAndLastName = firstName + lastName;
        //click on the button "Add new contact"
        openAddContactDialog();
        fillAddNewContactForm(firstName, lastName, description);
        //Click on the button "Save"
        saveNewContact();
        checkFieledsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        goToContactPageAndFillFilterField(firstName);
        checkCountRows(expectedCountRow);
        //Expected result: Created contact show with correct data in the contact table
    }

    private void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        //Filler by creation name
        fillField(firstName, (By.xpath("//*[@placeholder='Search...']")));
    }

}
