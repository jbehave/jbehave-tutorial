package org.jbehave.tutorials.etsy.pages;

import java.util.List;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResults extends BasePage {

    public SearchResults(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public String buyFirst(String thing) {
        List<WebElement> elems = getElems();
        for (int i = 0; i < elems.size(); i++) {
            WebElement elem = elems.get(i);
            String title = elem.getAttribute("title");
            if (title.toLowerCase().contains(thing.toLowerCase())) {
                elem.click();
                int ix = getCurrentUrl().indexOf("/listing/") + 9;
                String id = getCurrentUrl().substring(ix, ix + 8);
                // id.isNumber().shouldBe true, "no listing found";
                WebElement buyButton = findElements(By.xpath("//input[@value = 'Add to Cart']")).get(0);
                buyButton.click();
                return id;
            }
        }
        return "<not-bought>";
    }

    public List<WebElement> getElems() {
        return findElements(By.xpath("//a[@class = 'listing-thumb']"));
    }
}
