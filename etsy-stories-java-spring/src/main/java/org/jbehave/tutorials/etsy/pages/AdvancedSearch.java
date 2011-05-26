package org.jbehave.tutorials.etsy.pages;

import java.util.List;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdvancedSearch extends BasePage {

    public AdvancedSearch(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void go() {
        get("http://www.etsy.com/search_advanced.php");
    }

    public void go(String section) {
        go();
        findElement(By.xpath("//a[@title = '$section']")).click();
    }

    public void search(String thing) {
        findElement(By.id("search-query")).sendKeys(thing);
        findElement(By.id("search_submit")).click();
    }

    public void subCategory(String subCategory) {
        WebElement select = findElement(By.className("handmade"));
        List<WebElement> options = select.findElements(By.tagName("option"));
        for (int i = 0; i < options.size(); i++) {
            WebElement o = options.get(i);
            if (o.isSelected()) {
                o.setSelected();
            }
            if (o.getValue().equals(subCategory)) {
                o.setSelected();
                return;
            }
        }
    }

    public void searchFor(String thing) {
        WebElement field = findElement(By.id("search_query"));
        field.sendKeys(thing);
        field.submit();
    }
}
