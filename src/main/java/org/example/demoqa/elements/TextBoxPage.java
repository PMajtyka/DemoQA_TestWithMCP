package org.example.demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class TextBoxPage extends BasePage {
    public final Locator fullName;
    public final Locator email;
    public final Locator currentAddress;
    public final Locator permanentAddress;
    public final Locator submitButton;
    public final Locator output;

    public TextBoxPage(Page page) {
        super(page);
        fullName = page.getByPlaceholder("Full Name");
        email = page.getByPlaceholder("name@example.com");
        currentAddress = page.getByPlaceholder("Current Address");
        permanentAddress = page.locator("#permanentAddress");
        submitButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));
        output = page.locator("#output");
    }

    public void submitForm(String name, String emailAddress, String address, String permanent) {
        fullName.fill(name);
        email.fill(emailAddress);
        currentAddress.fill(address);
        permanentAddress.fill(permanent);
        scrollTo(submitButton);
        submitButton.click();
    }

    public String getOutputText() { return textOf(output); }
}
