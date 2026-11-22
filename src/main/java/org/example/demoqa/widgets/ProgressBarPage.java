package org.example.demoqa.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class ProgressBarPage extends BasePage {
    public final Locator startStopButton;
    public final Locator resetButton;
    public final Locator progressBar;

    public ProgressBarPage(Page page) {
        super(page);
        startStopButton = page.locator("#startStopButton");
        resetButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reset"));
        progressBar = page.getByRole(AriaRole.PROGRESSBAR);
    }

    public void startOrStop() { startStopButton.click(); }
    public void reset() { resetButton.click(); }
    public String getProgressValue() { return progressBar.getAttribute("aria-valuenow"); }
    public String getStartStopLabel() { return startStopButton.innerText(); }
}
