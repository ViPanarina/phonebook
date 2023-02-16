package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateContactHelpers extends ContactHelpers {
    public CreateContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void openAddNewContactDialog() {
        openDialog(By.cssSelector("[href='/contacts']"));
    }

    public void fillAddNewContactForm(String firstName, String lastName, String description) {
        fillField(firstName, newContactFirstName);
        fillField(lastName, newContactLastName);
        fillField(description, newContactDescription);
    }

    public void saveNewContact() throws InterruptedException {
        driver.findElement(saveNewContactButton).click();
        setWait().until(ExpectedConditions.invisibilityOfElementLocated  // waiting 10 sec
                (By.xpath("//*[@role='dialog']")));
    }

}
