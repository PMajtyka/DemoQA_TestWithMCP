package org.example.demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class WebTablesPage extends BasePage {
    public final Locator addButton;
    public final Locator searchBox;
    public final Locator firstName;
    public final Locator lastName;
    public final Locator age;
    public final Locator email;
    public final Locator salary;
    public final Locator department;
    public final Locator submitButton;
    public final Locator tableRows;

    public WebTablesPage(Page page) {
        super(page);
        addButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add"));
        searchBox = page.getByPlaceholder("Type to search");
        firstName = page.locator("#firstName");
        lastName = page.locator("#lastName");
        age = page.locator("#age");
        email = page.locator("#userEmail");
        salary = page.locator("#salary");
        department = page.locator("#department");
        submitButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Submit"));
        tableRows = page.locator("tbody tr");
    }

    public void openAddRecordDialog() { addButton.click(); }

    public void addRecord(String givenName, String familyName, String years, String emailAddress,
                          String annualSalary, String team) {
        openAddRecordDialog();
        firstName.fill(givenName);
        lastName.fill(familyName);
        age.fill(years);
        email.fill(emailAddress);
        salary.fill(annualSalary);
        department.fill(team);
        submitButton.click();
    }

    public void search(String query) { searchBox.fill(query); }
    public int getTableRowsCount() { return tableRows.count(); }
}
