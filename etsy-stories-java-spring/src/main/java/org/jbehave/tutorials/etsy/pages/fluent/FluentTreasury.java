package org.jbehave.tutorials.etsy.pages.fluent;

import org.jbehave.tutorials.etsy.pages.Treasury;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.xpath;

public class FluentTreasury extends FluentPage implements Treasury {

    public FluentTreasury(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void chooseFirstGallery() {
        div(xpath("@class='item-treasury-info-box'")).h3().link().click();
    }
}
