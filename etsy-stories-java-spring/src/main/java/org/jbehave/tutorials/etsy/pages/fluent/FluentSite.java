package org.jbehave.tutorials.etsy.pages.fluent;

import org.jbehave.tutorials.etsy.pages.Site;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class FluentSite extends FluentPage implements Site {

    public FluentSite(WebDriverProvider webDriverProvider) {
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
