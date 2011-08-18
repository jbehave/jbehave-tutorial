package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.xpath;

public class Treasury extends BasePage {

    public Treasury(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void chooseFirstGallery() {
        div(xpath("@class='item-treasury-info-box'")).h3().link().click();
    }
}
