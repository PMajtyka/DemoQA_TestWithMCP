package org.example.demoqa.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class AutoCompletePage extends BasePage {
    public final Locator multipleColorInput;
    public final Locator singleColorInput;
    public final Locator suggestions;

    public AutoCompletePage(Page page) {
        super(page);
        multipleColorInput = page.locator("#autoCompleteMultipleInput");
        singleColorInput = page.locator("#autoCompleteSingleInput");
        suggestions = page.getByRole(AriaRole.OPTION);
    }

    public void addColor(String color) {
        multipleColorInput.fill(color);
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(color)).click();
    }

    public void chooseSingleColor(String color) {
        singleColorInput.fill(color);
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(color)).click();
    }

    public String getMultipleColors() { return multipleColorInput.inputValue(); }
    public String getSingleColor() { return singleColorInput.inputValue(); }
}
