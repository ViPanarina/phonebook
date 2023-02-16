package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditContactHelpers extends ContactHelpers {
    public EditContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void openEditForm() {
        clickOnVisibleElement(editContact);
    }

    public void editeLastNameAndDescription(String lastName, String description) {
        fillField(lastName, editLastNameField);
        fillField(description, editDescriptionField);
    }

    public void saveEditedContact() {
        clickOnVisibleElement(buttonSubmitEdition);
    }

    public void editContactInfoForm(String firstName, String lastName, String description) {
        fillField(firstName, (By.name("input-ec-firstName")));
        editeLastNameAndDescription(lastName, description);
    }
}
