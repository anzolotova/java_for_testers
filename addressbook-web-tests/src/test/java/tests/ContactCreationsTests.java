package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationsTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "First name")) {
//            for (var nickname : List.of("", "Nickname")) {
//                for (var mobilePhone : List.of("", "123")) {
//                    result.add(new ContactData().withfirstname(firstname).withNickname(nickname).withMobilePhone(mobilePhone));
//                }
//            }
//        }
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(20))
                .withNickname(CommonFunctions.randomString(30)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.jdbc().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.jdbc().getContactList();
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.idContact()), Integer.parseInt(o2.idContact()));
        };
        newContacts.sort(compareByIdContact);
        var maxId = newContacts.get(newContacts.size() - 1).idContact();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withIdContact(maxId));
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContactHBM(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.idContact()), Integer.parseInt(o2.idContact()));
        };
        newContacts.sort(compareByIdContact);
        var maxId = newContacts.get(newContacts.size() - 1).idContact();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withIdContact(maxId));
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultiContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.idContact()), Integer.parseInt(o2.idContact()));
        };
        newContacts.sort(compareByIdContact);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withIdContact(newContacts.get(newContacts.size() - 1).idContact()).withNickname("").withPhoto(""));
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newContacts, expectedList);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "First name'", "Middle name", "Last name", "Nickname", "Title", "Company", "Address", "888", "123", "456", "e-mail", "Homepage", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }

}