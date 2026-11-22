package org.example.demoqa.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

public class SliderPage extends BasePage {
    public final Locator slider;
    public final Locator sliderValue;

    public SliderPage(Page page) {
        super(page);
        slider = page.getByRole(com.microsoft.playwright.options.AriaRole.SLIDER);
        sliderValue = page.locator("#sliderValue");
    }

    public void setValue(int value) {
        int minimum = Integer.parseInt(slider.getAttribute("min"));
        int maximum = Integer.parseInt(slider.getAttribute("max"));
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException("Slider value must be between " + minimum + " and " + maximum);
        }
        int current = Integer.parseInt(slider.inputValue());
        slider.focus();
        String key = value < current ? "ArrowLeft" : "ArrowRight";
        while (current != value) {
            slider.press(key);
            current += value < current ? -1 : 1;
        }
    }

    public String getValue() { return sliderValue.inputValue(); }
}
