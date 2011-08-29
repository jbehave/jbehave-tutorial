package org.jbehave.tutorials.etsy.pages.fluent;

import org.jbehave.tutorials.etsy.pages.AdvancedSearch;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.*;

public class FluentAdvancedSearch extends FluentPage implements AdvancedSearch {

    public FluentAdvancedSearch(WebDriverProvider webDriverProvider) {
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
