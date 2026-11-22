package org.example.demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

import java.util.regex.Pattern;

public class ButtonsPage extends BasePage {
    public final Locator doubleClickButton;
    public final Locator rightClickButton;
    public final Locator clickButton;
    public final Locator doubleClickMessage;
    public final Locator rightClickMessage;
    public final Locator dynamicClickMessage;

    public ButtonsPage(Page page) {
        super(page);
        doubleClickButton = page.locator("#doubleClickBtn");
        rightClickButton = page.locator("#rightClickBtn");
        clickButton = page.getByRole(com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(Pattern.compile("^Click Me$")));
        doubleClickMessage = page.locator("#doubleClickMessage");
        rightClickMessage = page.locator("#rightClickMessage");
        dynamicClickMessage = page.locator("#dynamicClickMessage");
    }

    public void doubleClick() { doubleClickButton.dblclick(); }
    public void rightClick() { rightClickButton.click(new Locator.ClickOptions().setButton(
            com.microsoft.playwright.options.MouseButton.RIGHT)); }
    public void clickDynamicButton() { clickButton.click(); }
    public String getClickMessages() {
        return String.join(" ", textOf(doubleClickMessage), textOf(rightClickMessage),
                textOf(dynamicClickMessage)).trim();
    }
}
