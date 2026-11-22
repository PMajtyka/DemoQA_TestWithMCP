package org.example.demoqa.alerts;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class AlertsPage extends BasePage {
    public final Locator immediateAlertButton;
    public final Locator timedAlertButton;
    public final Locator confirmButton;
    public final Locator promptButton;
    public final Locator confirmResult;
    public final Locator promptResult;

    public AlertsPage(Page page) {
        super(page);
        immediateAlertButton = page.locator("#alertButton");
        timedAlertButton = page.locator("#timerAlertButton");
        confirmButton = page.locator("#confirmButton");
        promptButton = page.locator("#promtButton");
        confirmResult = page.locator("#confirmResult");
        promptResult = page.locator("#promptResult");
    }

    public void acceptImmediateAlert() {
        page.onceDialog(dialog -> dialog.accept());
        immediateAlertButton.click();
    }

    public void acceptTimedAlert() {
        page.onceDialog(dialog -> dialog.accept());
        timedAlertButton.click();
    }

    public void acceptConfirmation() {
        page.onceDialog(dialog -> dialog.accept());
        confirmButton.click();
    }

    public void dismissConfirmation() {
        page.onceDialog(dialog -> dialog.dismiss());
        confirmButton.click();
    }

    public void submitPrompt(String value) {
        page.onceDialog(dialog -> dialog.accept(value));
        promptButton.click();
    }

    public String getConfirmationResult() { return textOf(confirmResult); }
    public String getPromptResult() { return textOf(promptResult); }
}
