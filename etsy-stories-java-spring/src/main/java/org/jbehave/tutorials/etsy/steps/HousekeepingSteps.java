package org.jbehave.tutorials.etsy.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriverException;

public class HousekeepingSteps {

    private WebDriverProvider webDriverProvider;
    
    public HousekeepingSteps(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
        this.webDriverProvider.initialize();
    }

    @BeforeScenario
    public void emptyCart() {
        try {
            webDriverProvider.get().manage().deleteCookieNamed("cart");
        } catch (WebDriverException e) {
            // tis OK
        }
    }
}
