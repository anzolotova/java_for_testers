package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createNewAccount(String username, String email)
    {
        openSignupPage();
        type(By.name("username"),username);
        type(By.name("email"),email);
        click(By.cssSelector("input[type='submit'][value='Signup']"));
        accountRegistrationProcessed();
    }

    public void activateUserAccount(String url)
    {
        manager.driver().get(url);
        editAccount();
        updateUser();
    }

    private void updateUser() {
        click(By.cssSelector("button[type='submit'].btn-success"));
    }

    private void editAccount(){//(String username) {
        //type(By.name("realname"),username);
        type(By.name("password"),"password");
        type(By.name("password_confirm"),"password");
    }

    private void accountRegistrationProcessed() {
        click(By.cssSelector("a[href='login_page.php']")); //("a.btn-success[href*='login_page.php']")
    }

    private void openSignupPage() {
        click(By.cssSelector("a[href='signup_page.php']"));
    }

}
