package org.jbehave.tutorials.etsy.pages.fluent;

import org.jbehave.tutorials.etsy.pages.Site;
import org.jbehave.web.selenium.WebDriverProvider;
import org.seleniumhq.selenium.fluent.Period;

import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class FluentSite extends FluentPage implements Site {

    public FluentSite(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public int cartSize() {
        String cartSize = within(secs(2)).div(id("cart")).getText().replace("Cart", "").trim();
        if (cartSize.equals("")) {
            return 0;
        }
        return Integer.parseInt(cartSize);


    }

}
