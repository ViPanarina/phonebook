import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EditContactInformationTest extends Login {
    @DataProvider
    public Iterator<Object[]> editInformation() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Puh", "I like honey"});
        list.add(new Object[]{"Puhovski", "I don't like wasps"});
        return list.iterator();
    }

    Faker faker = new Faker();
    By contactsList = By.xpath("//div[@id='contacts-list']");
    By searchInput = By.id("input-search-contact");

    By foundContact = By.xpath("//div[@class=\"list-group\"]/..//button[@ng-reflect-router-link=\"/contacts/1726\"]/..//b");
    By contactInfoWindow = By.xpath("//div[@class=\"mt-1 tab-content\"]");
    By buttonEdit = By.xpath("//button[@id=\"btn-edit-contact\"]");
    By editLastNameField = By.name("input-ec-lastName");
    By editDescriptionField = By.name("input-ec-description");
    By buttonSubmitEdition = By.xpath("//div[@class=\"col-sm-3\"]/..//button[@type=\"submit\"]");

    private void fillEditContactInfo(String lastName, String description) {
        fillField(lastName, editLastNameField);
        fillField(description, editDescriptionField);
    }

    private void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        fillField(firstName, searchInput);
    }


    @Test(dataProvider = "editInformation")
    public void editContactInformation(String lastName, String description) {

        //String lastName = faker.internet().uuid();
        //String description = faker.internet().uuid();
        Number expectedCountRow = 1;

        isElementPresent(contactsList);
        isElementClickable(searchInput);
        goToContactPageAndFillFilterField("Винни");
        checkItemText(foundContact, "Винни", "err");
        isElementClickable(foundContact);
        isElementPresent(contactInfoWindow);
        isElementClickable(buttonEdit);
        isElementPresent(By.xpath("div[@class=\"card\"]"));
        fillEditContactInfo(lastName, description);
        isElementClickable(buttonSubmitEdition);
    }


}

