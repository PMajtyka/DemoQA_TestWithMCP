package org.example.demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class RadioButtonPage extends BasePage {
    public final Locator yesRadio;
    public final Locator impressiveRadio;
    public final Locator noRadio;
    public final Locator result;

    public RadioButtonPage(Page page) {
        super(page);
        yesRadio = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Yes"));
        impressiveRadio = page.getByRole(AriaRole.RADIO,
                new Page.GetByRoleOptions().setName("Impressive"));
        noRadio = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("No"));
        result = page.locator(".text-success");
    }

    public void chooseYes() { yesRadio.check(); }
    public void chooseImpressive() { impressiveRadio.check(); }
    public String getSelectedAnswer() { return textOf(result); }
}
