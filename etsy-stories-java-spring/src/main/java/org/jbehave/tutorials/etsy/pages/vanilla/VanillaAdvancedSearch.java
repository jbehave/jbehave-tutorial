package org.jbehave.tutorials.etsy.pages.vanilla;

import java.util.List;

import org.jbehave.tutorials.etsy.pages.AdvancedSearch;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class VanillaAdvancedSearch extends VanillaPage implements AdvancedSearch {

    public VanillaAdvancedSearch(WebDriverProvider webDriverProvider) {
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
        WebElement field = findElement(By.id("search_query"));
        field.sendKeys(thing);
        field.submit();
    }
}
