package org.example.demoqa.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class DatePickerPage extends BasePage {
    public final Locator dateInput;
    public final Locator dateTimeInput;
    public final Locator calendar;

    public DatePickerPage(Page page) {
        super(page);
        dateInput = page.locator("#datePickerMonthYearInput");
        dateTimeInput = page.locator("#dateAndTimePickerInput");
        calendar = page.locator(".react-datepicker");
    }

    public void setDate(String date) {
        dateInput.fill(date);
        dateInput.press("Enter");
    }

    public void setDateAndTime(String dateTime) {
        dateTimeInput.fill(dateTime);
        dateTimeInput.press("Enter");
    }

    public String getSelectedDate() { return dateInput.inputValue(); }
    public String getSelectedDateAndTime() { return dateTimeInput.inputValue(); }
    public boolean isCalendarVisible() { return calendar.isVisible(); }
}
