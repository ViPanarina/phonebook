import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver;
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.name("confirm-password");

    //before - работает до запуска теста (предустановка)

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest(){
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        //  driver.navigate().to("https://www.google.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void loginWithValidData(){
        driver.findElement(By.xpath("//*[@id=\"defaultRegisterFormEmail\"]")).sendKeys("test@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys("test@gmail.com");
        //driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("test@gmail.com");
        driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
        driver.findElement(By.cssSelector(".btn.btn-info")).click();
    }
    @Test
    public void registerNewUser(){
        String userdata = "vipan@gmail.com";
        driver.findElement(By.id("login-form")).isDisplayed();
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registration-form")).isDisplayed();
        fillField(userdata, emailField);
        fillField(userdata, passwordField);
        fillField(userdata, confirmPasswordField);
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
    }

    private void fillField(String userdata, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userdata);
    }


    //after - закрытие браузера
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        if(driver!= null){
            driver.quit();
        }
    }//

}


