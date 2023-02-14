package e2e.helpers;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreateContactHelpers extends ContactHelpers {
    public CreateContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void openAddNewContactDialog() {
        driver.findElement(contactButton).click();
        Assert.assertTrue(isElementPresent(dialogWindow));
    }

    public void fillAddNewContactForm(String firstName, String lastName, String description) {
        fillField(firstName, newContactFirstName);
        fillField(lastName, newContactLastName);
        fillField(description, newContactDescription);
    }

    public void saveNewContact() throws InterruptedException {
        driver.findElement(saveNewContactButton).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(assertSaveDialogWindow));
    }

}
