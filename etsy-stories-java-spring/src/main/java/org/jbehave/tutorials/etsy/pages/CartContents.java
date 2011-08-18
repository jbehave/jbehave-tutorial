package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class CartContents extends BasePage {

    public CartContents(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public boolean hasItem(String item) {
        get("http://www.etsy.com/cart");
        try {
            div(By.cssSelector("div#listing-" + item));
        } catch (RuntimeException ex) {
            return false;
        }
        return true;
    }

    public void removeItem() {
        get("http://www.etsy.com/cart");
        link(xpath("@rel = 'remove'")).click();
        System.out.println();
    }

}
