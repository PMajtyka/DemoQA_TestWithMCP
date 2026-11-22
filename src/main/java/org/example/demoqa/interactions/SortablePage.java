package org.example.demoqa.interactions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class SortablePage extends BasePage {
    public final Locator listTab;
    public final Locator gridTab;
    public final Locator sortableItems;

    public SortablePage(Page page) {
        super(page);
        listTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("List"));
        gridTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Grid"));
        sortableItems = page.locator("#demo-tabpane-list .list-group-item");
    }

    public void selectListView() { listTab.click(); }
    public void selectGridView() { gridTab.click(); }

    public void moveListItem(int fromIndex, int toIndex) {
        Locator source = sortableItems.nth(fromIndex);
        Locator target = sortableItems.nth(toIndex);
        scrollTo(source);
        source.dragTo(target);
    }

    public int getItemsCount() { return sortableItems.count(); }
    public String getItemText(int index) { return sortableItems.nth(index).innerText(); }
}
