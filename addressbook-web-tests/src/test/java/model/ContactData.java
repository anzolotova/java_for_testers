package model;

public record ContactData(String firstname, String middlename, String lastname, String nickname, String title,
                          String company, String address, String homePhone, String mobilePhone, String workPhone,
                          String email, String homepage) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.nickname, this.title,
                this.company, this.address, this.homePhone, mobilePhone, this.workPhone,
                this.email, this.homepage);
    }
}