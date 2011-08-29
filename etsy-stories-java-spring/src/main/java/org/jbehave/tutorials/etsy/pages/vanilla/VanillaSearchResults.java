package org.jbehave.tutorials.etsy.pages.vanilla;

import org.jbehave.tutorials.etsy.pages.SearchResults;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VanillaSearchResults extends VanillaPage implements SearchResults {

    public VanillaSearchResults(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public String buyFirst(String thing) {
        List<WebElement> elements = getResultsElements();
        for (WebElement e : elements) {
            String title = e.getAttribute("title");
            if (title.toLowerCase().contains(thing.toLowerCase())) {
                e.click();
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

    public int resultsFound() {
        return getResultsElements().size();
    }

    private List<WebElement> getResultsElements() {
        return findElements(By.xpath("//a[@class = 'listing-thumb']"));
    }

}
