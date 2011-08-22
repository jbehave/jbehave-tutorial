package org.jbehave.tutorials.etsy.pages;

import org.jbehave.tutorials.etsy.pages.fluent.FluentAdvancedSearch;
import org.jbehave.tutorials.etsy.pages.fluent.FluentBuy;
import org.jbehave.tutorials.etsy.pages.fluent.FluentCartContents;
import org.jbehave.tutorials.etsy.pages.fluent.FluentHome;
import org.jbehave.tutorials.etsy.pages.fluent.FluentSearchResults;
import org.jbehave.tutorials.etsy.pages.fluent.FluentSite;
import org.jbehave.tutorials.etsy.pages.fluent.FluentTreasury;
import org.jbehave.web.selenium.WebDriverProvider;

public class PageFactory {

    private WebDriverProvider webDriverProvider;

    public PageFactory(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    public AdvancedSearch newAdvancedSearch() {
        return new FluentAdvancedSearch(webDriverProvider);
    }

    public Home newHome() {
        return new FluentHome(webDriverProvider);
    }

    public Site newSite() {
        return new FluentSite(webDriverProvider);
    }

    public SearchResults newSearchResults() {
        return new FluentSearchResults(webDriverProvider);
    }

    public CartContents newCartContents() {
        return new FluentCartContents(webDriverProvider);
    }

    public Buy newBuy() {
        return new FluentBuy(webDriverProvider);
    }

    public Treasury newTreasury() {
        return new FluentTreasury(webDriverProvider);
    }

}
