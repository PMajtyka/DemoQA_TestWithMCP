package org.example.demoqa.interactions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class SelectablePage extends BasePage {
    public final Locator listTab;
    public final Locator gridTab;
    public final Locator selectableItems;
    public final Locator selectedItems;

    public SelectablePage(Page page) {
        super(page);
        listTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("List"));
        gridTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Grid"));
        selectableItems = page.locator("#demo-tabpane-list .list-group-item");
        selectedItems = page.locator("#demo-tabpane-list .list-group-item.active");
    }

    public void selectListItem(String label) {
        page.getByText(label, new Page.GetByTextOptions().setExact(true)).click();
    }

    public void selectGridView() { gridTab.click(); }
    public void selectListView() { listTab.click(); }
    public int getSelectableItemsCount() { return selectableItems.count(); }
    public int getSelectedItemsCount() { return selectedItems.count(); }
}
