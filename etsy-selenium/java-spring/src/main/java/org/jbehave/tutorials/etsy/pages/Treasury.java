package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.className;

public class Treasury extends WebDriverPage {

    public Treasury(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void chooseFirstGallery() {
        findElement(className("item-treasury-info-box")).click();
    }

}
