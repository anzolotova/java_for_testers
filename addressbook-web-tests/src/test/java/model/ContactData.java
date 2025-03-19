package model;

public record ContactData(String idContact, String firstname, String middlename, String lastname, String nickname, String title,
                          String company, String address, String homePhone, String mobilePhone, String workPhone,
                          String email, String homepage) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withIdContact(String idContact) {
        return new ContactData(idContact, this.firstname, this.middlename, this.lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone,
                this.email, this.homepage);
    }

    public ContactData withfirstname(String firstname) {
        return new ContactData(this.idContact, firstname, this.middlename, this.lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone,
                this.email, this.homepage);
    }

    public ContactData withlastname(String lastname) {
        return new ContactData(this.idContact, this.firstname, this.middlename, lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone,
                this.email, this.homepage);
    }

    public ContactData withnickname(String nickname) {
        return new ContactData(this.idContact, this.firstname, this.middlename, this.lastname, nickname, this.title,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone,
                this.email, this.homepage);
    }
    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.idContact, this.firstname, this.middlename, this.lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, mobilePhone, this.workPhone,
                this.email, this.homepage);
    }
}