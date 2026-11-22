package org.example.demoqa.interactions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import org.example.demoqa.common.BasePage;

public class ResizablePage extends BasePage {
    public final Locator resizableBox;
    public final Locator resizeHandle;

    public ResizablePage(Page page) {
        super(page);
        resizableBox = page.locator("#resizableBoxWithRestriction");
        resizeHandle = resizableBox.locator(".react-resizable-handle");
    }

    public void resizeBy(int horizontalPixels, int verticalPixels) {
        scrollTo(resizeHandle);
        BoundingBox bounds = resizeHandle.boundingBox();
        double startX = bounds.x + bounds.width / 2;
        double startY = bounds.y + bounds.height / 2;
        page.mouse().move(startX, startY);
        page.mouse().down();
        page.mouse().move(startX + horizontalPixels, startY + verticalPixels);
        page.mouse().up();
    }

    public String getBoxDimensions() {
        return resizableBox.evaluate("element => `${Math.round(element.getBoundingClientRect().width)}x${Math.round(element.getBoundingClientRect().height)}`")
                .toString();
    }
}
