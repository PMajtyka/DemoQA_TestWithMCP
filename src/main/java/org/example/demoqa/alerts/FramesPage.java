package org.example.demoqa.alerts;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class FramesPage extends BasePage {
    public final FrameLocator firstFrame;
    public final FrameLocator secondFrame;
    public final Locator firstFrameHeading;
    public final Locator secondFrameHeading;

    public FramesPage(Page page) {
        super(page);
        firstFrame = frame("#frame1");
        secondFrame = frame("#frame2");
        firstFrameHeading = firstFrame.locator("#sampleHeading");
        secondFrameHeading = secondFrame.locator("#sampleHeading");
    }

    public String getFirstFrameText() { return firstFrameHeading.innerText(); }
    public String getSecondFrameText() { return secondFrameHeading.innerText(); }
}
