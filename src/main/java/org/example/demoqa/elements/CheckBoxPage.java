package org.example.demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class CheckBoxPage extends BasePage {
    public final Locator homeExpandToggle;
    public final Locator homeCheckbox;
    public final Locator result;

    public CheckBoxPage(Page page) {
        super(page);
        homeExpandToggle = page.locator(".rc-tree-switcher");
        homeCheckbox = page.getByRole(com.microsoft.playwright.options.AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Select Home"));
        result = page.locator("#result");
    }

    public void expandHomeNode() { homeExpandToggle.click(); }
    public void selectHome() { scrollTo(homeCheckbox); homeCheckbox.check(); }
    public String getSelectionText() { return textOf(result); }
}
