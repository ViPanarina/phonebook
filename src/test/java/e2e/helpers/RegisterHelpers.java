package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterHelpers extends CommonHelpers {

    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.name("confirm-password");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    public By errorMessageBlock = By.id("error-message");
    public By errorEmailMessageBlock = By.id("email-error-invalid");
    public By errorPasswordMaxLengthMessageBlock = By.id("password-error-maxlength");

    public RegisterHelpers(WebDriver driver) {
        super(driver);
    }

    public void fillRegistrationForm(String userData, String passwordData) {
        fillField(userData, emailField);
        fillField(passwordData, passwordField);
        fillField(passwordData, confirmPasswordField);
    }

    public void goToRegistrationPage() {
        Assert.assertTrue(isElementPresent(loginForm));
        driver.findElement(userRegistrationLink).click();
        Assert.assertTrue(isElementPresent(registrationForm));
    }

    public void clickSignUpButton() {
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
    }

    public void checkErrorMessage(By locator, String expectedErrorMessage) {
        String err = "Actual error message isn't equal expected ";
        checkItemText(locator, expectedErrorMessage, err);
    }
}
