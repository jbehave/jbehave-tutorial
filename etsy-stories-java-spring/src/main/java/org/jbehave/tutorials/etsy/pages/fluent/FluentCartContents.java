package org.jbehave.tutorials.etsy.pages.fluent;

import org.jbehave.tutorials.etsy.pages.CartContents;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class FluentCartContents extends FluentPage implements CartContents {

    public FluentCartContents(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public boolean hasItem(String item) {
        get("http://www.etsy.com/cart");
        try {
            div(cssSelector("div#listing-" + item));
        } catch (RuntimeException ex) {
            return false;
        }
        return true;
    }

    public void removeItem() {
        get("http://www.etsy.com/cart");
        link(xpath("//a[@rel = 'remove']")).click();
    }


    public int cartSize() {
        get("http://www.etsy.com/cart");
        try {
            return Integer.parseInt(findElement(By.xpath("//div[@class='count']")).getText());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return 0;
        }
    }

}
