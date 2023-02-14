package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends TestBase {
    Faker faker = new Faker();
    
    @Test
    public void registerNewUserWithValidData() {
        //Arrange
        String userData = faker.internet().emailAddress();
        String passwordData = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        //Act
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, passwordData);
        app.getRegister().clickSignUpButton();
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage);
    }


    //--------- Negative test

    @Test
    public void registerNewUserWithInvalidData() {
        //Arrange
        String userData = faker.internet().password();
        String passwordData = faker.internet().emailAddress();
        String expectedEmailErrorMessage = "Email must be a valid email address.";
        String expectedPasswordErrorMessage = "Password must be no longer than 20 characters.";
        //Act
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, passwordData);
        Assert.assertFalse(app.getRegister().isElementPresent(app.getRegister().errorMessageBlock));
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorEmailMessageBlock, expectedEmailErrorMessage);
        app.getRegister().checkErrorMessage(app.getRegister().errorPasswordMaxLengthMessageBlock, expectedPasswordErrorMessage);
    }

    @Test
    public void registerExistingUser() {
        //Arrange
        String userData = "test@gmail.com";
        String passwordData = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists e2e.Login now?";
        //Act
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, passwordData);
        app.getRegister().clickSignUpButton();
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage);
    }
}

