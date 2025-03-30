package model;

public record ContactData(String idContact, String firstname, String middlename, String lastname, String nickname, String title,
                          String company, String address, String homePhone, String mobilePhone, String workPhone,
                          String email, String homepage, String photo) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withIdContact(String idContact) {
        return new ContactData(idContact, this.firstname, this.middlename, this.lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone,
                this.email, this.homepage,this.photo);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.idContact, firstname, this.middlename, this.lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone,
                this.email, this.homepage,this.photo);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.idContact, this.firstname, this.middlename, lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone,
                this.email, this.homepage,this.photo);
    }

    public ContactData withNickname(String nickname) {
        return new ContactData(this.idContact, this.firstname, this.middlename, this.lastname, nickname, this.title,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone,
                this.email, this.homepage,this.photo);
    }
    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.idContact, this.firstname, this.middlename, this.lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, mobilePhone, this.workPhone,
                this.email, this.homepage,this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.idContact, this.firstname, this.middlename, this.lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone,
                this.email, this.homepage, photo);
    }
}