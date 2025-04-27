package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactCreationsTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    public static List<ContactData> randomContact() {
        return List.of(new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(20))
                .withNickname(CommonFunctions.randomString(30)));
    }

    @ParameterizedTest
    @MethodSource("randomContact")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        var extraContacts = newContacts.stream().filter(c -> ! oldContacts.contains(c)).toList();
        var newId = extraContacts.get(0).idContact();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withIdContact(newId));
        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }

    @ParameterizedTest
    @MethodSource("randomContact")
    public void canCreateContactInGroup(ContactData contact) {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var newId = newRelated.get(newRelated.size() - 1).idContact();
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.withIdContact(newId));
        Assertions.assertEquals(Set.copyOf(newRelated), Set.copyOf(expectedList));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultiContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withIdContact(newContacts.get(newContacts.size() - 1).idContact()).withNickname("").withPhoto(""));
        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "First name'", "Middle name", "Last name", "Nickname", "Title", "Company", "Address", "888", "123", "456", "e-mail", "", "", "Homepage", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Assertions.assertEquals(newContacts, oldContacts);
    }

}