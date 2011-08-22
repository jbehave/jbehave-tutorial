package org.jbehave.tutorials.etsy.pages.vanilla;

import org.jbehave.tutorials.etsy.pages.Site;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class VanillaSite extends VanillaPage implements Site {

    public VanillaSite(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public int cartSize() {
        try {
            return Integer.parseInt(findElement(By.xpath("//div[@class='count']")).getText());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return 0;
        }
    }

}
