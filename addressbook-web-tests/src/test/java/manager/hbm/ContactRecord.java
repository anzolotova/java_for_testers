package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String nickname;
    public String middlename = "";
    public String company = "";
    public String title = "";
    public String address = "";
    public String home = "";
    public String mobile = "";
    public String work = "";
    public String fax = "";
    public String email = "";
    public String email2 = "";
    public String email3 = "";
    public String homepage = "";
    public ContactRecord(){}
    public ContactRecord(int id, String firstname, String middlename, String lastname, String nickname, String title,
                         String company, String address, String home, String mobile, String work,
                         String email, String email2, String email3, String homepage) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
    }
}
