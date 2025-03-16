package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationsTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "First name")) {
            for (var nickname : List.of("", "Nickname")) {
                for (var mobilePhone : List.of("", "123")) {
                    result.add(new ContactData(firstname, "Middle name", "Last name", nickname, "Title", "Company", "Address", "888", mobilePhone, "456", "e-mail", "Homepage"));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultiContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("First name'", "Middle name", "Last name", "Nickname", "Title", "Company", "Address", "888", "123", "456", "e-mail", "Homepage")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }

}