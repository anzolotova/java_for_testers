package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }


    public void createContact(ContactData contact) {
        openConactAddPage();
        fillContactForm(contact);
        submitContactCreation();
        openHomePage();
    }

    public void deleteContact() {
        selectContact();
        deleteSelectedContact();
        openHomePage();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("nickname"), contact.nickname());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.homePhone());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("work"), contact.workPhone());
        type(By.name("email"), contact.email());
        type(By.name("homepage"), contact.homepage());
    }


    public void openConactAddPage() {
        if (!manager.isElementPresent(By.name("submit"))) {
            click(By.linkText("add new"));
        }
    }

    public void openHomePage() {
        click(By.linkText("home"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }


    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void deleteSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }
}
