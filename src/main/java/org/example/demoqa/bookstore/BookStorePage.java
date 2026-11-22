package org.example.demoqa.bookstore;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class BookStorePage extends BasePage {
    public final Locator searchBox;
    public final Locator bookRows;
    public final Locator loginButton;
    public final Locator nextPageButton;
    public final Locator previousPageButton;

    public BookStorePage(Page page) {
        super(page);
        searchBox = page.getByPlaceholder("Type to search");
        bookRows = page.locator("tbody tr");
        loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        nextPageButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next"));
        previousPageButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Previous"));
    }

    public void searchBooks(String query) { searchBox.fill(query); }

    public void openBook(String title) {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(title)).click();
    }

    public void openLogin() { loginButton.click(); }
    public int getBookRowsCount() { return bookRows.count(); }
    public String getBookListText() { return String.join(System.lineSeparator(), bookRows.allInnerTexts()); }
}
