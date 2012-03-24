package org.jbehave.tutorials.etsy.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriverException;

public class LifecycleSteps {

    private final WebDriverProvider webDriverProvider;

    public LifecycleSteps(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    @BeforeScenario
    public void emptyCart() {
        try {
            webDriverProvider.get().manage().deleteCookieNamed("uaid");
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }
}
