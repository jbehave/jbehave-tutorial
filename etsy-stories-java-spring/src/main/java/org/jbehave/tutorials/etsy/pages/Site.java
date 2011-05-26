package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Site extends BasePage {

    public Site(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public int cartSize() {
        try {
            return Integer.parseInt(findElement(By.xpath("//div[@class='count']")).getText());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return 0;
        }

    }

}
