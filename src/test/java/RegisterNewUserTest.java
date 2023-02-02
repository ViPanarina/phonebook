import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends TestBase {


    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.name("confirm-password");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By errorMessageBlock = By.id("error-message");
    By errorEmailMessageBlock = By.id("email-error-invalid");
    By errorPasswordMaxLengthMessageBlock = By.id("password-error-maxlength");
    Faker faker = new Faker();


    private void fillRegistrationForm(String userData, String passwordData) {
        fillField(userData, emailField);
        fillField(passwordData, passwordField);
        fillField(passwordData, confirmPasswordField);
    }

    private void goToRegistrationPage() {
        Assert.assertTrue(isElementPresent(loginForm));
        driver.findElement(userRegistrationLink).click();
        Assert.assertTrue(isElementPresent(registrationForm));
    }

    private void clickSignUpButton() {
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
    }

    private void checkErrorMessage(By locator, String expectedErrorMessage) {
        String err = "Actual error message isn't equal expected ";
        checkItemText(locator, expectedErrorMessage, err);
    }


    @Test
    public void registerNewUserWithValidData() {
        //Arrange
        String userData = faker.internet().emailAddress();
        String passwordData = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        //Act
        goToRegistrationPage();
        fillRegistrationForm(userData, passwordData);
        clickSignUpButton();
        //Assert
        checkErrorMessage(errorMessageBlock, expectedErrorMessage);
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
        goToRegistrationPage();
        fillRegistrationForm(userData, passwordData);
        Assert.assertFalse(isElementPresent(errorMessageBlock));
        //Assert
        checkErrorMessage(errorEmailMessageBlock, expectedEmailErrorMessage);
        checkErrorMessage(errorPasswordMaxLengthMessageBlock, expectedPasswordErrorMessage);

    }

    @Test
    public void registerExistingUser() {
        //Arrange
        String userData = "test@gmail.com";
        String passwordData = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists Login now?";
        //Act
        goToRegistrationPage();
        fillRegistrationForm(userData, passwordData);
        clickSignUpButton();
        //Assert
        checkErrorMessage(errorMessageBlock, expectedErrorMessage);
    }


}

