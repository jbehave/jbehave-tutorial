package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.*;

public class Home extends WebDriverPage {

    public Home(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void go() {
        get("http://www.etsy.com");
    }

    public void go(String section) {
        go();
        findElement(xpath("@title = '" + section + "'")).click();
    }

    public void search(String thing) {
        findElement(id("search-query")).sendKeys(thing);
        findElement(className("btn")).click();
    }

    public void goToBuySection() {
        findElement(linkText("Etsy")).click();
    }
}
