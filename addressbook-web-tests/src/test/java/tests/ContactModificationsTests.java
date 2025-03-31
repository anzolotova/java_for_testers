package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationsTests extends TestBase{

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testDataContact = new ContactData().withFirstname("new firstname");
        app.contacts().modifyContact(oldContacts.get(index), testDataContact);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testDataContact.withIdContact(oldContacts.get(index).idContact()));
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.idContact()), Integer.parseInt(o2.idContact()));
        };
        newContacts.sort(compareByIdContact);
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newContacts, expectedList);
    }


    @Test
    void addContactToGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withFirstname("contact with group"));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName("group with contact"));
        }
        var contact = app.hbm().getContactList();
        var group = app.hbm().getGroupList();
        var rnd = new Random();
        var i_contact = rnd.nextInt(contact.size());
        var i_group = rnd.nextInt(group.size());
        var oldRelated = app.hbm().getContactsInGroup(group.get(i_group));
        app.contacts().addContactToGroup(contact.get(i_contact), group.get(i_group));
        var newRelated = app.hbm().getContactsInGroup(group.get(i_group));
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.idContact()), Integer.parseInt(o2.idContact()));
        };
        newRelated.sort(compareByIdContact);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.get(i_contact));
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newRelated, expectedList);
    }


    @Test
    void deleteContactFromGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withFirstname("contact with group"));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName("group with contact"));
        }
        var contact = app.hbm().getContactList();
        var group = app.hbm().getGroupList();
        var rnd = new Random();
        var i_contact = rnd.nextInt(contact.size());
        var i_group = rnd.nextInt(group.size());
        var oldRelated = app.hbm().getContactsInGroup(group.get(i_group));
        app.contacts().deleteContactFromGroup(contact.get(i_contact), group.get(i_group));
        var newRelated = app.hbm().getContactsInGroup(group.get(i_group));
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.idContact()), Integer.parseInt(o2.idContact()));
        };
        newRelated.sort(compareByIdContact);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(contact.get(i_contact));
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newRelated, expectedList);
    }
}
