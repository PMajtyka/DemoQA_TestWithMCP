package org.example.demoqa.alerts;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class NestedFramesPage extends BasePage {
    public final FrameLocator parentFrame;
    public final FrameLocator childFrame;
    public final Locator parentFrameBody;
    public final Locator childFrameBody;

    public NestedFramesPage(Page page) {
        super(page);
        parentFrame = frame("#frame1");
        childFrame = parentFrame.frameLocator("iframe");
        parentFrameBody = parentFrame.locator("body");
        childFrameBody = childFrame.locator("body");
    }

    public String getParentFrameText() { return parentFrameBody.innerText(); }
    public String getChildFrameText() { return childFrameBody.innerText(); }
}
