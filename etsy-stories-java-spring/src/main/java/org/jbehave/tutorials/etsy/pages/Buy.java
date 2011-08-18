package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.id;

public class Buy extends BasePage {

    public Buy(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void selectTreasury() {
        link(id("treasury-panel-button")).click();
    }
}
