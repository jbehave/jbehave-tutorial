package org.jbehave.tutorials.etsy.pages.vanilla;

import org.jbehave.tutorials.etsy.pages.Buy;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class VanillaBuy extends VanillaPage implements Buy {

    public VanillaBuy(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void selectTreasury() {
        findElement(By.id("treasury-panel-button")).click();
    }

}
