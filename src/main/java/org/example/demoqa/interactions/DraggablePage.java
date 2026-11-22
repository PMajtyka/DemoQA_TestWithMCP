package org.example.demoqa.interactions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class DraggablePage extends BasePage {
    public final Locator simpleTab;
    public final Locator axisRestrictedTab;
    public final Locator containerRestrictedTab;
    public final Locator cursorStyleTab;
    public final Locator draggable;

    public DraggablePage(Page page) {
        super(page);
        simpleTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Simple"));
        axisRestrictedTab = page.getByRole(AriaRole.TAB,
                new Page.GetByRoleOptions().setName("Axis Restricted"));
        containerRestrictedTab = page.getByRole(AriaRole.TAB,
                new Page.GetByRoleOptions().setName("Container Restricted"));
        cursorStyleTab = page.getByRole(AriaRole.TAB,
                new Page.GetByRoleOptions().setName("Cursor Style"));
        draggable = page.locator("#dragBox");
    }

    public void selectTab(String tabName) {
        page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(tabName)).click();
    }

    public void dragBy(int horizontalPixels, int verticalPixels) {
        scrollTo(draggable);
        BoundingBox bounds = draggable.boundingBox();
        double startX = bounds.x + bounds.width / 2;
        double startY = bounds.y + bounds.height / 2;
        page.mouse().move(startX, startY);
        page.mouse().down();
        page.mouse().move(startX + horizontalPixels, startY + verticalPixels);
        page.mouse().up();
    }

    public String getDraggableText() { return draggable.innerText(); }
}
