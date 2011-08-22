package org.jbehave.tutorials.etsy.pages.vanilla;

import org.jbehave.tutorials.etsy.pages.Treasury;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VanillaTreasury extends VanillaPage implements Treasury {

    public VanillaTreasury(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }
    
    public void chooseFirstGallery() {
        WebElement element = findElement(By.xpath("//div[@class='item-treasury-info-box']/h3/a"));
        element.click();
    }

}
