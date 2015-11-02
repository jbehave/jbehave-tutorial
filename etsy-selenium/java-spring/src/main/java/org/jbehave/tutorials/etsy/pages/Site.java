package org.jbehave.tutorials.etsy.pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class Site extends FluentWebDriverPage {

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
