package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.*;

public class Home extends FluentWebDriverPage {

    public Home(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void go() {
        get("http://www.etsy.com");
    }

    public void go(String section) {
        go();
        link(xpath("@title = '" + section + "'")).click();
    }

    public void search(String thing) {
        input(id("search-query")).sendKeys(thing);
        button(className("btn")).click();
    }

    public void goToBuySection() {
        link(linkText("Etsy")).click();
    }
}
