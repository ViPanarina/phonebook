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
    Faker faker = new Faker();

    @Test
    public void registerNewUser() {
        //Arrange
        String userData = faker.internet().emailAddress();
        String passwordData = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        //Act
        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(passwordData, passwordField);
        fillField(passwordData, confirmPasswordField);
        driver.findElement(loginButton).click();
        String actualMessage = driver.findElement(errorMessageBlock).getText();
        //Assert
        String err = "Actual error message isn't equal expected ";
        Assert.assertEquals(actualMessage, expectedErrorMessage, "err");
    }

}
