package org.jbehave.tutorials.etsy.pages.fluent;

import java.util.List;

import org.jbehave.tutorials.etsy.pages.AdvancedSearch;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.tagName;
import static org.openqa.selenium.By.xpath;

public class FluentAdvancedSearch extends FluentPage implements AdvancedSearch {

    public FluentAdvancedSearch(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void go() {
        get("http://www.etsy.com/search_advanced.php");
    }

    public void go(String section) {
        go();
        link(xpath("@title = '$section'")).click();
    }

    public void search(String thing) {
        input(id("search-query")).sendKeys(thing);
        input(id("search_submit")).click();
    }

    public void subCategory(String subCategory) {
        WebElement select = findElement(className("handmade"));
        List<WebElement> options = select.findElements(tagName("option"));
        for (int i = 0; i < options.size(); i++) {
            WebElement o = options.get(i);
            Select selected = new Select(o);
            if (o.isSelected()) {
                selected.selectByIndex(i);
            }
            if (o.getAttribute("value").equals(subCategory)) {
                selected.selectByValue(subCategory);
                return;
            }
        }
    }

    public void searchFor(String thing) {
        input(id("search_query")).sendKeys(thing).submit();
    }
}
