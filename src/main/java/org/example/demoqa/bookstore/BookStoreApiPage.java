package org.example.demoqa.bookstore;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class BookStoreApiPage extends BasePage {
    public final Locator authorizeButton;
    public final Locator operationBlocks;

    public BookStoreApiPage(Page page) {
        super(page);
        authorizeButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Authorize"));
        operationBlocks = page.locator(".opblock");
    }

    public void openAuthorizeDialog() { authorizeButton.click(); }

    public void expandOperation(String method, String path) {
        Locator operation = operationBlocks
                .filter(new Locator.FilterOptions().setHasText(method.toUpperCase()))
                .filter(new Locator.FilterOptions().setHasText(path));
        operation.locator(".opblock-summary-control").click();
    }

    public int getOperationCount() { return operationBlocks.count(); }
    public String getApiTitle() { return page.title(); }
}
