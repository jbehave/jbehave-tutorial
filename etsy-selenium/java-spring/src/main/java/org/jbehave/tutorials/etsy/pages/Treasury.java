package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.className;

public class Treasury extends FluentWebDriverPage {

    public Treasury(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void chooseFirstGallery() {
        div(className("item-treasury-info-box")).h3().link().click();
    }

}
