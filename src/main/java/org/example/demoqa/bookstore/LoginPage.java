package org.example.demoqa.bookstore;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends BasePage {
    public final Locator username;
    public final Locator password;
    public final Locator loginButton;
    public final Locator newUserButton;
    public final Locator errorMessage;

    public LoginPage(Page page) {
        super(page);
        username = page.locator("#userName");
        password = page.locator("#password");
        loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        newUserButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New User"));
        errorMessage = page.locator("#name");
    }

    public void login(String user, String secret) {
        username.fill(user);
        password.fill(secret);
        loginButton.click();
    }

    public void openRegistration() { newUserButton.click(); }
    public String getErrorMessage() { return textOf(errorMessage); }

    public boolean isUsernameInvalid() {
        return (boolean) username.evaluate("element => !element.validity.valid");
    }

    public boolean isPasswordInvalid() {
        return (boolean) password.evaluate("element => !element.validity.valid");
    }
}
