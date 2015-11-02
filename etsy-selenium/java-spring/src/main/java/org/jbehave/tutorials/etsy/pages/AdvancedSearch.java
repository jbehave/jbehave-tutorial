package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class AdvancedSearch extends WebDriverPage {

    public AdvancedSearch(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void go() {
        get("http://www.etsy.com/search_advanced.php");
    }

    public void go(String section) {
        go();
        findElement(xpath("@title = '" + section + "'")).click();
    }

    public void search(String thing) {
        findElement(id("search-query")).sendKeys(thing);
        findElement(id("search_submit")).click();
    }

    public void subCategory(String subCategory) {
        findElement(By.partialLinkText(subCategory)).click();
    }

    public void searchFor(String thing) {
        WebElement searchQuery = findElement(id("search-query"));
        searchQuery.sendKeys(thing);
        searchQuery.submit();
    }
    
}
