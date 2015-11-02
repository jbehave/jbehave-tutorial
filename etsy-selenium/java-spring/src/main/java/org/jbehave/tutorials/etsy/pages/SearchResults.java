package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentMatcher;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import java.util.regex.Matcher;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class SearchResults extends FluentWebDriverPage {

    public SearchResults(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public String buyFirst(String thing) {
        FluentWebElement firstElement = getResultElements().get(0);
        firstElement.click();
        int ix = getCurrentUrl().indexOf("/listing/") + 9;
        String id = getCurrentUrl().substring(ix, ix + 9);
        // id.isNumber().shouldBe true, "no listing found";
        button(className("btn-transaction")).click();
        return id;
    }

    public  int resultsFound() {
        return getResultElements().size();
    }

    public WebElement searchResult(){
        return findElement(By.className("strong"));
    }

    private FluentWebElements getResultElements() {
        return within(secs(2)).divs(className("block-grid-item"));
    }

    private FluentMatcher lowerCaseTitleContaining(final String thing) {
        return new FluentMatcher() {
            public boolean matches(WebElement webElement) {
                return webElement.getText().toLowerCase().contains(thing);
            }
        };
    }
}