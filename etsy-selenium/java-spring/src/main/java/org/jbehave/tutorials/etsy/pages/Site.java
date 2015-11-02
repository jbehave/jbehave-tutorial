package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class Site extends WebDriverPage {

    public Site(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public int cartSize() {
        int csize = 0;

        try {
            String cartSize = findElement(By.className("count")).getText();
            if (cartSize != null) {
                csize = Integer.valueOf(cartSize);
            }
            return csize;
        } catch (NoSuchElementException nsee) {
            return csize;
        }

    }

}
