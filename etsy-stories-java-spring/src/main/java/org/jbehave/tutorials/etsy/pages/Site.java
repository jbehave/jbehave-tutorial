package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Site extends BasePage {

    public Site(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public int cartSize() {
        String cartSize = div(By.id("cart")).getText().replace("Cart", "").trim();
        if (cartSize.equals("")) {
            return 0;
        }
        return Integer.parseInt(cartSize);


    }

}
