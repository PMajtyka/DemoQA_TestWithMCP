package org.example.demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class DynamicPropertiesPage extends BasePage {
    public final Locator enableAfterDelayButton;
    public final Locator colorChangeButton;
    public final Locator visibleAfterDelayButton;

    public DynamicPropertiesPage(Page page) {
        super(page);
        enableAfterDelayButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Will enable 5 seconds"));
        colorChangeButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Color Change"));
        visibleAfterDelayButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Visible After 5 Seconds"));
    }

    public void waitForDelayedButton() { enableAfterDelayButton.waitFor(); }
    public void clickDelayedButton() { enableAfterDelayButton.click(); }
    public void hoverColorChangeButton() { colorChangeButton.hover(); }
    public boolean isDelayedButtonEnabled() { return enableAfterDelayButton.isEnabled(); }
    public boolean isDelayedButtonVisible() { return visibleAfterDelayButton.isVisible(); }
    public String getColorChangeButtonColor() {
        return colorChangeButton.evaluate("element => getComputedStyle(element).color").toString();
    }
}
