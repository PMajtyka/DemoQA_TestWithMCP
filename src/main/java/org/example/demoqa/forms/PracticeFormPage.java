package org.example.demoqa.forms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;
import com.microsoft.playwright.options.AriaRole;

public class PracticeFormPage extends BasePage {
    public final Locator firstName;
    public final Locator lastName;
    public final Locator email;
    public final Locator mobile;
    public final Locator dateOfBirth;
    public final Locator subjects;
    public final Locator currentAddress;
    public final Locator uploadPicture;
    public final Locator submitButton;
    public final Locator submissionDialog;

    public PracticeFormPage(Page page) {
        super(page);
        firstName = page.getByPlaceholder("First Name");
        lastName = page.getByPlaceholder("Last Name");
        email = page.getByPlaceholder("name@example.com");
        mobile = page.getByPlaceholder("Mobile Number");
        dateOfBirth = page.locator("#dateOfBirthInput");
        subjects = page.locator("#subjectsInput");
        currentAddress = page.getByPlaceholder("Current Address");
        uploadPicture = page.locator("#uploadPicture");
        submitButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));
        submissionDialog = page.getByRole(AriaRole.DIALOG);
    }

    public void fillStudentDetails(String givenName, String familyName, String emailAddress,
                                   String phoneNumber, String address) {
        firstName.fill(givenName);
        lastName.fill(familyName);
        email.fill(emailAddress);
        mobile.fill(phoneNumber);
        currentAddress.fill(address);
    }

    public void chooseGender(String gender) {
        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName(gender)).check();
    }

    public void addSubject(String subject) {
        subjects.fill(subject);
        subjects.press("Enter");
    }

    public void addHobby(String hobby) {
        page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(hobby)).check();
    }

    public void submitForm() {
        scrollTo(submitButton);
        submitButton.click();
    }

    public String getSubmissionSummary() { return textOf(submissionDialog); }
}
