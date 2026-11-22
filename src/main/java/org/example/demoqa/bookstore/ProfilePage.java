package org.example.demoqa.bookstore;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class ProfilePage extends BasePage {
    public final Locator loginNotice;
    public final Locator username;
    public final Locator logoutButton;
    public final Locator deleteAccountButton;
    public final Locator userBooks;

    public ProfilePage(Page page) {
        super(page);
        loginNotice = page.getByText(
                "Currently you are not logged into the Book Store application",
                new Page.GetByTextOptions().setExact(false));
        username = page.locator("#userName-value");
        logoutButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout"));
        deleteAccountButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Delete Account"));
        userBooks = page.locator("tbody tr");
    }

    public void logout() { logoutButton.click(); }
    public void deleteAccount() { deleteAccountButton.click(); }
    public boolean isLoginNoticeVisible() { return loginNotice.isVisible(); }
    public String getUsername() { return textOf(username); }
    public int getBookRowsCount() { return userBooks.count(); }
}
