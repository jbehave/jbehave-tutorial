package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.className;

public class SearchResults extends WebDriverPage {

    public SearchResults(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public String buyFirst(String thing) {
        WebElement firstElement = getResultElements().get(0);
        firstElement.click();
        int ix = getCurrentUrl().indexOf("/listing/") + 9;
        String id = getCurrentUrl().substring(ix, ix + 9);
        // id.isNumber().shouldBe true, "no listing found";
        findElement(className("btn-transaction")).click();
        return id;
    }

    public WebElement searchResult(){
        return findElement(By.className("strong"));
    }

    private List<WebElement> getResultElements() {
        return findElements(className("block-grid-item"));
    }
}