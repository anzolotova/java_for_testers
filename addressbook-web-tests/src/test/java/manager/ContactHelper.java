package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }


    public void createContact(ContactData contact) {
        openContactAddPage();
        fillContactForm(contact);
        submitContactCreation();
        openHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        openContactAddPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        openHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void deleteContact(ContactData contact) {
        openHomePage();
        updateHomePage();
        selectContact(contact);
        deleteSelectedContacts();
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
        //attach(By.name("photo"), contact.photo());
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        updateHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        openHomePage();
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        openHomePage();
        updateHomePage();
        selectContact(contact);
        selectAddGroupName(group);
        importContactToGroup();
        openHomePage();
    }

    public void deleteContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        updateHomePage();
        selectDeleteGroupName(group);
        selectContact(contact);
        removeContactFromGroup();
        openHomePage();
    }

    private void updateHomePage() {
        click(By.linkText("home"));
        WebElement dropdown = manager.driver.findElement(By.name("group"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("[all]");
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector("td.center > a[href^='edit.php?id=" + contact.idContact() + "']"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    public void openContactAddPage() {
        if (!manager.isElementPresent(By.name("submit"))) {
            click(By.linkText("add new"));
        }
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("add"))) {
            click(By.linkText("home"));
        }
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }


    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.idContact())));
    }

    private void deleteSelectedContacts() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        click(By.xpath("//input[@id=\'MassCB\']"));
        deleteSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs) {
            var checkbox = tr.findElement(By.cssSelector("tr[name='entry'] input[type='checkbox']"));
            var idContact = checkbox.getAttribute("id");
            var td = tr.findElements(By.tagName("td"));
            var lastname = td.get(1).getText();
            var firstname = td.get(2).getText();
            contacts.add(new ContactData().withIdContact(idContact).withLastname(lastname).withFirstname(firstname));
        }
        return contacts;
    }

    private void selectAddGroupName(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void importContactToGroup() {
        click(By.name("add"));
    }


    private void selectDeleteGroupName(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void removeContactFromGroup() {
        click(By.name("remove"));
    }
}
