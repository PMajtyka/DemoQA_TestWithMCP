package org.example.demoqa.alerts;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.demoqa.common.BasePage;

public class BrowserWindowsPage extends BasePage {
    public final Locator newTabButton;
    public final Locator newWindowButton;
    public final Locator messageWindowButton;

    public BrowserWindowsPage(Page page) {
        super(page);
        newTabButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("New Tab"));
        newWindowButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("New Window"));
        messageWindowButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("New Window Message"));
    }

    public Page openNewTab() { return page.waitForPopup(newTabButton::click); }
    public Page openNewWindow() { return page.waitForPopup(newWindowButton::click); }
    public Page openMessageWindow() { return page.waitForPopup(messageWindowButton::click); }
}
