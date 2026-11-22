package org.example.demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class BrokenLinksPage extends BasePage {
    public final Locator images;
    public final Locator validLink;
    public final Locator brokenLink;

    public BrokenLinksPage(Page page) {
        super(page);
        images = page.locator("img");
        validLink = page.getByText("Click Here for Valid Link", new Page.GetByTextOptions().setExact(true));
        brokenLink = page.getByText("Click Here for Broken Link", new Page.GetByTextOptions().setExact(true));
    }

    public void openValidLink() { validLink.click(); }
    public void openBrokenLink() { brokenLink.click(); }
    public int getImageCount() { return images.count(); }
    public int getBrokenImageCount() {
        return (int) images.evaluateAll("(items) => items.filter(img => !img.complete || img.naturalWidth === 0).length");
    }
}
