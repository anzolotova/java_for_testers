package manager;

import manager.hbm.GroupRecord;
import manager.hbm.ContactRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
//                        .addAnnotatedClass(Book.class)
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertGrouptList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.group_id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }


    public List<GroupData> getGroupList() {
        return convertGrouptList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }


    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    static List<ContactData> convertListContact(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static ContactData convert(ContactRecord record) {
        //return new ContactData("" + record.id, record.firstname, "", record.lastname, record.nickname, "", "", "", "", "", "", "", "", "");
        return new ContactData().withIdContact("" + record.id)
                .withFirstname(record.firstname)
                .withLastname(record.lastname)
                .withNickname(record.nickname)
                .withHomePhone(record.home)
                .withMobilePhone(record.mobile)
                .withWorkPhone(record.work)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3)
                .withAddress(record.address);
    }

    private static ContactRecord convert(ContactData data) {
        var idContact = data.idContact();
        if ("".equals(idContact)) {
            idContact = "0";
        }
        return new ContactRecord(Integer.parseInt(idContact), data.firstname(), data.middlename(), data.lastname(), data.nickname(), data.title(), data.company(), data.address(), data.homePhone(), data.mobilePhone(), data.workPhone(), data.email(), data.email2(), data.email3(), data.homepage());
    }

    public List<ContactData> getContactList() {
        return convertListContact(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }


    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
           return convertListContact(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
