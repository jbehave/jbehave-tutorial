package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class CartContents extends WebDriverPage {

    public CartContents(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public boolean hasItem(String item) {
        try {
            findElement(id("listing-" + item));
        } catch (RuntimeException ex) {
            return false;
        }
        return true;
    }

    public void removeItem() {
        get("http://www.etsy.com/cart");
        findElement(xpath("//a[@rel = 'remove']")).click();
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
