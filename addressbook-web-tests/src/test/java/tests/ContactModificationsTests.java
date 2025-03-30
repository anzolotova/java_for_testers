package tests;

import model.ContactData;
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
}
