package org.example.demoqa.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class SelectMenuPage extends BasePage {
    public final Locator customSelects;
    public final Locator oldStyleSelect;
    public final Locator standardMultiSelect;

    public SelectMenuPage(Page page) {
        super(page);
        customSelects = page.getByRole(AriaRole.COMBOBOX);
        oldStyleSelect = page.locator("#oldSelectMenu");
        standardMultiSelect = page.locator("#cars");
    }

    public void selectCustomOption(int selectIndex, String optionName) {
        customSelects.nth(selectIndex).click();
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(optionName)).click();
    }

    public void selectOldStyleOption(String label) {
        String value = oldStyleSelect.locator("option")
                .filter(new Locator.FilterOptions().setHasText(label)).getAttribute("value");
        oldStyleSelect.selectOption(value);
    }

    public void selectCar(String value) { standardMultiSelect.selectOption(value); }
    public String getOldStyleSelection() { return oldStyleSelect.inputValue(); }
}
