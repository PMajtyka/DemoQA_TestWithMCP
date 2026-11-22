package org.example.demoqa.alerts;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class ModalDialogsPage extends BasePage {
    public final Locator smallModalButton;
    public final Locator largeModalButton;
    public final Locator modal;
    public final Locator modalTitle;
    public final Locator modalBody;
    public final Locator closeSmallModalButton;
    public final Locator closeLargeModalButton;

    public ModalDialogsPage(Page page) {
        super(page);
        smallModalButton = page.locator("#showSmallModal");
        largeModalButton = page.locator("#showLargeModal");
        modal = page.getByRole(AriaRole.DIALOG);
        modalTitle = page.locator(".modal-title");
        modalBody = page.locator(".modal-body");
        closeSmallModalButton = page.locator("#closeSmallModal");
        closeLargeModalButton = page.locator("#closeLargeModal");
    }

    public void openSmallModal() { smallModalButton.click(); }
    public void openLargeModal() { largeModalButton.click(); }
    public void closeModal() {
        if (closeSmallModalButton.isVisible()) closeSmallModalButton.click();
        else closeLargeModalButton.click();
    }
    public String getModalTitle() { return textOf(modalTitle); }
    public String getModalBody() { return textOf(modalBody); }
    public boolean isModalVisible() { return modal.isVisible(); }
}
