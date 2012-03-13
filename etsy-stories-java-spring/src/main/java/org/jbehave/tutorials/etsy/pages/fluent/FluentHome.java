package org.jbehave.tutorials.etsy.pages.fluent;

import org.jbehave.tutorials.etsy.pages.Home;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.xpath;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class FluentHome extends FluentPage implements Home {

    public FluentHome(WebDriverProvider webDriverProvider) {
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
//        within(secs(100)).title().shouldBe("kittens");
        div(id("search-facet")).click();
        li(className("all")).click();
        input(id("search-query")).sendKeys(thing);
        button(id("search_submit")).click();
    }

    public void goToBuySection() {
        link(linkText("Buy")).click();
    }
}
