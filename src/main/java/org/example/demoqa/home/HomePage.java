package org.example.demoqa.home;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class HomePage extends BasePage {
    public final Locator elementsSection;
    public final Locator formsSection;
    public final Locator alertsWindowsSection;
    public final Locator widgetsSection;
    public final Locator interactionsSection;
    public final Locator bookStoreSection;

    public HomePage(Page page) {
        super(page);
        elementsSection = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Elements"));
        formsSection = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Forms"));
        alertsWindowsSection = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Alerts, Frame & Windows"));
        widgetsSection = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Widgets"));
        interactionsSection = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Interactions"));
        bookStoreSection = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Book Store Application"));
    }

    public void openElements() { elementsSection.click(); }
    public void openForms() { formsSection.click(); }
    public void openAlertsWindows() { alertsWindowsSection.click(); }
    public void openWidgets() { widgetsSection.click(); }
    public void openInteractions() { interactionsSection.click(); }
    public void openBookStore() { bookStoreSection.click(); }

    public Locator section(String accessibleName) {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(accessibleName));
    }

    public int getSectionCount() {
        return page.getByRole(AriaRole.LINK).filter(
                new Locator.FilterOptions().setHas(page.locator("h5"))).count();
    }
}
