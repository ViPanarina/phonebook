package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import org.openqa.selenium.By;
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
        String expectedErrorMessage = "Error! User already exists Login now?";
        //Act
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, passwordData);
        app.getRegister().clickSignUpButton();
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage);
    }

    @Test
    public void registerNewUserWithSpaceInTheFirstName() {
        String userData = " ";
        String passwordData = faker.internet().password();
        String expectedEmailErrorMessage = "Email is required.";

        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, passwordData);
        Assert.assertTrue(app.getRegister().isElementPresent(By.id("email-error-required")));
        app.getRegister().checkErrorMessage(By.id("email-error-required"), expectedEmailErrorMessage);
    }

    @Test
    public void registerNewUserWithShortPassword() {
        String userData = "test@gmail.com";
        String passwordData = "45454";
        String expectedEmailErrorMessage = "Password must be at least 8 characters.";

        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, passwordData);
        Assert.assertTrue(app.getRegister().isElementPresent(By.id("password-error-minlength")));
        app.getRegister().checkErrorMessage(By.id("password-error-minlength"), expectedEmailErrorMessage);
    }
}

