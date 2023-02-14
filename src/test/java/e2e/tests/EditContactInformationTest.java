package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class EditContactInformationTest extends TestBase {
    @Test(dataProvider = "changeLastNameAndDescription", dataProviderClass = DataProviders.class)
    public void editContactInfo(String lastName, String description) {
        String firstName = "a3a31992-231e-48bd-8f23-0c6191fd7705";

        app.getLogin().login();
        app.getEditContact().changeLanguage();
        app.getEditContact().goToContactPageAndFillFilterField(firstName);
        app.getEditContact().checkCountRows(1);
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();
        app.getEditContact().editeLastNameAndDescription(lastName, description);
        app.getEditContact().saveEditedContact();
        app.getEditContact().checkFieldsOnContactInfo(firstName, lastName, description);
    }

    @Test(dataProvider = "editContactInfoWithCSV", dataProviderClass = DataProviders.class)
    public void editContactInfoWithCSV(String lastName, String description) {
        String firstName = "a3a31992-231e-48bd-8f23-0c6191fd7705";

        app.getLogin().login();
        app.getEditContact().changeLanguage();
        app.getEditContact().goToContactPageAndFillFilterField(firstName);
        app.getEditContact().checkCountRows(1);
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();
        app.getEditContact().editeLastNameAndDescription(lastName, description);
        app.getEditContact().saveEditedContact();
        app.getEditContact().checkFieldsOnContactInfo(firstName, lastName, description);
    }
}


