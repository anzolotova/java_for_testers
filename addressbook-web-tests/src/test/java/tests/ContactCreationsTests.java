package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationsTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("First name", "Middle name", "Last name", "Nickname", "Title", "Company", "Address", "888", "123", "456", "e-mail", "Homepage"));
    }

    @Test
    public void canCreateContactWithEmptyName() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithPhoneOnly() {
        app.contacts().createContact(new ContactData().withMobilePhone("123456789"));
    }


}