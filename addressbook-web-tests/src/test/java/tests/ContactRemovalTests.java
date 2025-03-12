package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.isElementPresent(By.name("add"))) {
            app.contacts().openHomePage();
        }
        if (!app.isElementPresent(By.name("selected[]"))) {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().deleteContact();
    }

}
