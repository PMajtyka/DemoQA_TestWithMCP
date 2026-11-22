package org.example.demoqa.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class TabsPage extends BasePage {
    public final Locator whatTab;
    public final Locator originTab;
    public final Locator useTab;
    public final Locator moreTab;
    public final Locator selectedPanel;

    public TabsPage(Page page) {
        super(page);
        whatTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("What"));
        originTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Origin"));
        useTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Use"));
        moreTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("More"));
        selectedPanel = page.locator(".tab-content .active");
    }

    public void selectTab(String name) {
        page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(name)).click();
    }

    public String getSelectedTabText() { return textOf(selectedPanel); }
    public String getSelectedTabName() {
        return page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setSelected(true)).innerText();
    }
}
