package org.example.demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class LinksPage extends BasePage {
    public final Locator homeLink;
    public final Locator createdLink;
    public final Locator noContentLink;
    public final Locator movedLink;
    public final Locator badRequestLink;
    public final Locator unauthorizedLink;
    public final Locator forbiddenLink;
    public final Locator notFoundLink;
    public final Locator responseMessage;

    public LinksPage(Page page) {
        super(page);
        homeLink = page.locator("#simpleLink");
        createdLink = page.locator("#created");
        noContentLink = page.locator("#no-content");
        movedLink = page.locator("#moved");
        badRequestLink = page.locator("#bad-request");
        unauthorizedLink = page.locator("#unauthorized");
        forbiddenLink = page.locator("#forbidden");
        notFoundLink = page.locator("#invalid-url");
        responseMessage = page.locator("#linkResponse");
    }

    public Page openHomeInNewTab() {
        return page.waitForPopup(homeLink::click);
    }

    public void requestCreatedResponse() { createdLink.click(); }
    public void requestNoContentResponse() { noContentLink.click(); }
    public String getApiResponseMessage() { return textOf(responseMessage); }
}
