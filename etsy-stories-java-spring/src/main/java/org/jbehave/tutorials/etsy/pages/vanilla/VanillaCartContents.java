package org.jbehave.tutorials.etsy.pages.vanilla;

import org.jbehave.tutorials.etsy.pages.CartContents;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class VanillaCartContents extends VanillaPage implements CartContents {

    public VanillaCartContents(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public boolean hasItem(String item) {
        get("http://www.etsy.com/cart");
        try {
            findElement(By.cssSelector("div#listing-" + item));
        } catch (RuntimeException ex) {
            return false;
        }
        return true;
    }

    public void removeItem() {
        get("http://www.etsy.com/cart");
        findElement(By.xpath("//a[@rel = 'remove']")).click();
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
