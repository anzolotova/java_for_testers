package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withMobilePhone(CommonFunctions.randomPhoneNumber.apply(10))
                    .withWorkPhone(CommonFunctions.randomPhoneNumber.apply(10)));
        }
        app.contacts().updateHomePage();
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testAddress() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withAddress(CommonFunctions.randomString(10)));
        }
        app.contacts().updateHomePage();
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var addresses = app.contacts().getAddress(contact);
        var expected = Stream.of(contact.address())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, addresses);
    }

    @Test
    void testEmail() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withEmail(CommonFunctions.randomString(5))
                    .withEmail3(CommonFunctions.randomString(5)));
        }
        app.contacts().updateHomePage();
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEMail(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testPhonesAddressEmail() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstname(CommonFunctions.randomString(5))
                    .withMobilePhone(CommonFunctions.randomPhoneNumber.apply(10))
                    .withWorkPhone(CommonFunctions.randomPhoneNumber.apply(5))
                    .withAddress(CommonFunctions.randomString(10))
                    .withEmail(CommonFunctions.randomString(5) + "@zolotova.com")
                    .withEmail3(CommonFunctions.randomString(5) + "@zolotova.com"));
        }
        app.contacts().updateHomePage();
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expectedPhones = Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedPhones, phones, "Проверка телефонов");
        var addresses = app.contacts().getAddress(contact);
        var expectedAddresses = Stream.of(contact.address())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedAddresses, addresses, "Проверка адреса");
        var emails = app.contacts().getEMail(contact);
        var expectedEmails = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedEmails, emails, "Проверка email");
    }

    @Test
    void testPhonesAllContacts() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withMobilePhone(CommonFunctions.randomString(5))
                    .withWorkPhone(CommonFunctions.randomString(5)));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::idContact, contact ->
            Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone())
                    .filter(s -> s != null && ! "".equals(s))
                    .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }
}
