package org.jbehave.tutorials.etsy.pages.fluent;

import org.jbehave.tutorials.etsy.pages.Buy;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.id;

public class FluentBuy extends FluentPage implements Buy {

    public FluentBuy(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void selectTreasury() {
        link(id("treasury-panel-button")).click();
    }
}
