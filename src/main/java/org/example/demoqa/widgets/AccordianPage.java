package org.example.demoqa.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class AccordianPage extends BasePage {
    public final Locator firstSection;
    public final Locator secondSection;
    public final Locator thirdSection;
    public final Locator expandedPanel;

    public AccordianPage(Page page) {
        super(page);
        firstSection = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("What is Lorem Ipsum?"));
        secondSection = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Where does it come from?"));
        thirdSection = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Why do we use it?"));
        expandedPanel = page.locator(".collapse.show");
    }

    public void openSection(String title) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(title)).click();
    }

    public String getExpandedSectionText() { return textOf(expandedPanel); }
}
