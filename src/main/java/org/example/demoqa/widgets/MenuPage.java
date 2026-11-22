package org.example.demoqa.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class MenuPage extends BasePage {
    public final Locator mainMenuItems;

    public MenuPage(Page page) {
        super(page);
        mainMenuItems = page.locator("#nav li");
    }

    public void hoverMenuItem(String label) {
        page.getByText(label, new Page.GetByTextOptions().setExact(true)).hover();
    }

    public int getVisibleMenuItemsCount() {
        return (int) mainMenuItems.evaluateAll("(items) => items.filter(item => item.getClientRects().length > 0).length");
    }
}
