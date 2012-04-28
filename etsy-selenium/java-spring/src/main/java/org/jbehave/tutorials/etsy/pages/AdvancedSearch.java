package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class AdvancedSearch extends FluentWebDriverPage {

    public AdvancedSearch(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void go() {
        get("http://www.etsy.com/search_advanced.php");
    }

    public void go(String section) {
        go();
        link(xpath("@title = '" + section + "'")).click();
    }

    public void search(String thing) {
        input(id("search-query")).sendKeys(thing);
        input(id("search_submit")).click();
    }

    public void subCategory(String subCategory) {
        select(className("handmade")).selectByValue(subCategory.toLowerCase());
    }

    public void searchFor(String thing) {
        input(id("search_query")).sendKeys(thing).submit();
    }
    
}
