package org.example.demoqa.common;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {
    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    public void scrollTo(Locator locator) {
        locator.scrollIntoViewIfNeeded();
    }

    public FrameLocator frame(String selector) {
        return page.frameLocator(selector);
    }

    public void hideFloatingAds() {
        page.addStyleTag(new Page.AddStyleTagOptions().setContent(
                "#fixedban,#adplus-anchor,[id^='Ad.Plus-'],iframe[id^='google_ads_iframe_'],.adsbygoogle{display:none!important;}"));
    }

    protected String textOf(Locator locator) {
        return locator.isVisible() ? locator.innerText() : "";
    }
}
