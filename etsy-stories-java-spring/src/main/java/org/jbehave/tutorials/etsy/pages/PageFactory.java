package org.jbehave.tutorials.etsy.pages;

import org.jbehave.tutorials.etsy.pages.fluent.FluentAdvancedSearch;
import org.jbehave.tutorials.etsy.pages.fluent.FluentBuy;
import org.jbehave.tutorials.etsy.pages.fluent.FluentCartContents;
import org.jbehave.tutorials.etsy.pages.fluent.FluentHome;
import org.jbehave.tutorials.etsy.pages.fluent.FluentSearchResults;
import org.jbehave.tutorials.etsy.pages.fluent.FluentSite;
import org.jbehave.tutorials.etsy.pages.fluent.FluentTreasury;
import org.jbehave.tutorials.etsy.pages.vanilla.VanillaAdvancedSearch;
import org.jbehave.tutorials.etsy.pages.vanilla.VanillaBuy;
import org.jbehave.tutorials.etsy.pages.vanilla.VanillaCartContents;
import org.jbehave.tutorials.etsy.pages.vanilla.VanillaHome;
import org.jbehave.tutorials.etsy.pages.vanilla.VanillaSearchResults;
import org.jbehave.tutorials.etsy.pages.vanilla.VanillaSite;
import org.jbehave.tutorials.etsy.pages.vanilla.VanillaTreasury;
import org.jbehave.web.selenium.WebDriverProvider;

public class PageFactory {

    private final WebDriverProvider webDriverProvider;
    private final boolean fluentStyle;

    public PageFactory(WebDriverProvider webDriverProvider) {
        this(webDriverProvider, Boolean.parseBoolean(System.getProperty("FLUENT_STYLE", "true")));
    }
    
    public PageFactory(WebDriverProvider webDriverProvider, boolean fluentStyle) {
        this.webDriverProvider = webDriverProvider;
        this.fluentStyle = fluentStyle;
    }

    public AdvancedSearch newAdvancedSearch() {
        if ( fluentStyle ){
            return new FluentAdvancedSearch(webDriverProvider);
        }
        return new VanillaAdvancedSearch(webDriverProvider);
    }

    public Home newHome() {
        if ( fluentStyle ){
            return new FluentHome(webDriverProvider);
        }
        return new VanillaHome(webDriverProvider);
    }

    public Site newSite() {
        if ( fluentStyle ){
            return new FluentSite(webDriverProvider);
        }
        return new VanillaSite(webDriverProvider);
    }

    public SearchResults newSearchResults() {
        if ( fluentStyle ){
            return new FluentSearchResults(webDriverProvider);
        }
        return new VanillaSearchResults(webDriverProvider);
    }

    public CartContents newCartContents() {
        if ( fluentStyle ){
            return new FluentCartContents(webDriverProvider);
        }
        return new VanillaCartContents(webDriverProvider);
    }

    public Buy newBuy() {
        if ( fluentStyle ){
            return new FluentBuy(webDriverProvider);
        }
        return new VanillaBuy(webDriverProvider);
    }

    public Treasury newTreasury() {
        if ( fluentStyle ){
            return new FluentTreasury(webDriverProvider);
        }
        return new VanillaTreasury(webDriverProvider);
    }

}
