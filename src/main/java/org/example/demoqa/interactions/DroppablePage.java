package org.example.demoqa.interactions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class DroppablePage extends BasePage {
    public final Locator simpleTab;
    public final Locator acceptTab;
    public final Locator preventPropagationTab;
    public final Locator revertDraggableTab;
    public final Locator draggable;
    public final Locator dropTarget;

    public DroppablePage(Page page) {
        super(page);
        simpleTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Simple"));
        acceptTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Accept"));
        preventPropagationTab = page.getByRole(AriaRole.TAB,
                new Page.GetByRoleOptions().setName("Prevent Propogation"));
        revertDraggableTab = page.getByRole(AriaRole.TAB,
                new Page.GetByRoleOptions().setName("Revert Draggable"));
        draggable = page.locator("#draggable");
        dropTarget = page.locator("#droppable");
    }

    public void selectTab(String tabName) {
        page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(tabName)).click();
    }

    public void dropItem() {
        scrollTo(draggable);
        page.dragAndDrop("#draggable", "#droppable");
    }

    public String getDropTargetText() { return dropTarget.innerText(); }
}
