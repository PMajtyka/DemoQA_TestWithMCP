package org.example.demoqa.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class ToolTipsPage extends BasePage {
    public final Locator tooltipButton;
    public final Locator tooltipTextField;
    public final Locator tooltip;

    public ToolTipsPage(Page page) {
        super(page);
        tooltipButton = page.locator("#toolTipButton");
        tooltipTextField = page.locator("#toolTipTextField");
        tooltip = page.locator(".tooltip-inner");
    }

    public void hoverButton() { tooltipButton.hover(); }
    public void hoverTextField() { tooltipTextField.hover(); }
    public String getTooltipText() { return textOf(tooltip); }
}
