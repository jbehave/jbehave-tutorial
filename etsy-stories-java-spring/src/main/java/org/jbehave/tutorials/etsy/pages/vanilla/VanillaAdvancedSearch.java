package org.jbehave.tutorials.etsy.pages.vanilla;

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
        findElement(By.xpath("//a[@title = '" + section + "']")).click();
    }

    public void search(String thing) {
        findElement(By.id("search-query")).sendKeys(thing);
        findElement(By.id("search_submit")).click();
    }

    public void subCategory(String subCategory) {
        WebElement select = findElement(By.xpath("//select[@class ='handmade']"));
        Select selected = new Select(select);
        selected.selectByValue(subCategory.toLowerCase());
    }

    public void searchFor(String thing) {
        WebElement field = findElement(By.id("search_query"));
        field.sendKeys(thing);
        field.submit();
    }
}
