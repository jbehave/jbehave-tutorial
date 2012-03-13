package org.jbehave.tutorials.etsy.pages.fluent;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.*;

public abstract class FluentPage extends WebDriverPage implements FluentWebDriver {

    public FluentPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    private FluentWebDriverImpl fluentWebDriver() {
        return new FluentWebDriverImpl(webDriver());
    }
    
    public FluentWebElement span() {
        return fluentWebDriver().span();
    }

    public FluentWebElement span(By by) {
        return fluentWebDriver().span(by);
    }

    public FluentWebElements spans() {
        return fluentWebDriver().spans();
    }

    public FluentWebElements spans(By by) {
        return fluentWebDriver().spans(by);
    }

    public FluentWebElement div() {
        return fluentWebDriver().div();
    }

    public FluentWebElement div(By by) {
        return fluentWebDriver().div(by);
    }

    public FluentWebElements divs() {
        return fluentWebDriver().divs();
    }

    public FluentWebElements divs(By by) {
        return fluentWebDriver().divs(by);
    }

    public FluentWebElement button() {
        return fluentWebDriver().button();
    }

    public FluentWebElement button(By by) {
        return fluentWebDriver().button(by);
    }

    public FluentWebElements buttons() {
        return fluentWebDriver().buttons();
    }

    public FluentWebElements buttons(By by) {
        return fluentWebDriver().buttons(by);
    }

    public FluentWebElement link() {
        return fluentWebDriver().link();
    }

    public FluentWebElement link(By by) {
        return fluentWebDriver().link(by);
    }

    public FluentWebElements links() {
        return fluentWebDriver().links();
    }

    public FluentWebElements links(By by) {
        return fluentWebDriver().links(by);
    }

    public FluentWebElement input() {
        return fluentWebDriver().input();
    }

    public FluentWebElement input(By by) {
        return fluentWebDriver().input(by);
    }

    public FluentWebElements inputs() {
        return fluentWebDriver().inputs();
    }

    public FluentWebElements inputs(By by) {
        return fluentWebDriver().inputs(by);
    }

    public FluentSelect select() {
        return fluentWebDriver().select();
    }

    public FluentSelect select(By by) {
        return fluentWebDriver().select(by);
    }

    public FluentWebElements selects() {
        return fluentWebDriver().selects();
    }

    public FluentWebElements selects(By by) {
        return fluentWebDriver().selects(by);
    }

    public FluentWebElement li() {
        return fluentWebDriver().li();
    }

    public FluentWebElement li(By by) {
        return fluentWebDriver().li(by);
    }

    public FluentWebElements lis() {
        return fluentWebDriver().lis();
    }

    public FluentWebElements lis(By by) {
        return fluentWebDriver().lis(by);
    }

    public FluentWebElement h1() {
        return fluentWebDriver().h1();
    }

    public FluentWebElement h1(By by) {
        return fluentWebDriver().h1(by);
    }

    public FluentWebElements h1s() {
        return fluentWebDriver().h1s();
    }

    public FluentWebElements h1s(By by) {
        return fluentWebDriver().h1s(by);
    }

    public FluentWebElement h2() {
        return fluentWebDriver().h2();
    }

    public FluentWebElement h2(By by) {
        return fluentWebDriver().h2(by);
    }

    public FluentWebElements h2s() {
        return fluentWebDriver().h2s();
    }

    public FluentWebElements h2s(By by) {
        return fluentWebDriver().h2s(by);
    }

    public FluentWebElement h3() {
        return fluentWebDriver().h3();
    }

    public FluentWebElement h3(By by) {
        return fluentWebDriver().h3(by);
    }

    public FluentWebElements h3s() {
        return fluentWebDriver().h3s();
    }

    public FluentWebElements h3s(By by) {
        return fluentWebDriver().h3s(by);
    }

    public FluentWebElement h4() {
        return fluentWebDriver().h4();
    }

    public FluentWebElement h4(By by) {
        return fluentWebDriver().h4(by);
    }

    public FluentWebElements h4s() {
        return fluentWebDriver().h4s();
    }

    public FluentWebElements h4s(By by) {
        return fluentWebDriver().h4s(by);
    }

    public FluentWebElement p() {
        return fluentWebDriver().p();
    }

    public FluentWebElement p(By by) {
        return fluentWebDriver().p(by);
    }

    public FluentWebElements ps() {
        return fluentWebDriver().ps();
    }

    public FluentWebElements ps(By by) {
        return fluentWebDriver().ps(by);
    }

    public FluentWebElement img() {
        return fluentWebDriver().img();
    }

    public FluentWebElement img(By by) {
        return fluentWebDriver().img(by);
    }

    public FluentWebElements imgs() {
        return fluentWebDriver().imgs();
    }

    public FluentWebElements imgs(By by) {
        return fluentWebDriver().imgs(by);
    }

    public FluentWebElement table() {
        return fluentWebDriver().table();
    }

    public FluentWebElement table(By by) {
        return fluentWebDriver().table(by);
    }

    public FluentWebElements tables() {
        return fluentWebDriver().tables();
    }

    public FluentWebElements tables(By by) {
        return fluentWebDriver().tables(by);
    }

    public FluentWebElement tr() {
        return fluentWebDriver().tr();
    }

    public FluentWebElement tr(By by) {
        return fluentWebDriver().tr(by);
    }

    public FluentWebElements trs() {
        return fluentWebDriver().trs();
    }

    public FluentWebElements trs(By by) {
        return fluentWebDriver().trs(by);
    }

    public FluentWebElement td() {
        return fluentWebDriver().td();
    }

    public FluentWebElement td(By by) {
        return fluentWebDriver().td(by);
    }

    public FluentWebElements tds() {
        return fluentWebDriver().tds();
    }

    public FluentWebElements tds(By by) {
        return fluentWebDriver().tds(by);
    }

    public FluentWebElement th() {
        return fluentWebDriver().th();
    }

    public FluentWebElement th(By by) {
        return fluentWebDriver().th(by);
    }

    public FluentWebElements ths() {
        return fluentWebDriver().ths();
    }

    public FluentWebElements ths(By by) {
        return fluentWebDriver().ths(by);
    }

    public FluentWebElement ul() {
        return fluentWebDriver().ul();
    }

    public FluentWebElement ul(By by) {
        return fluentWebDriver().ul(by);
    }

    public FluentWebElements uls() {
        return fluentWebDriver().uls();
    }

    public FluentWebElements uls(By by) {
        return fluentWebDriver().uls(by);
    }

    public FluentWebElement ol() {
        return fluentWebDriver().ol();
    }

    public FluentWebElement ol(By by) {
        return fluentWebDriver().ol(by);
    }

    public FluentWebElements ols() {
        return fluentWebDriver().ols();
    }

    public FluentWebElements ols(By by) {
        return fluentWebDriver().ols(by);
    }

    public FluentWebElement form() {
        return fluentWebDriver().form();
    }

    public FluentWebElement form(By by) {
        return fluentWebDriver().form(by);
    }

    public FluentWebElements forms() {
        return fluentWebDriver().forms();
    }

    public FluentWebElements forms(By by) {
        return fluentWebDriver().forms(by);
    }

    public FluentWebElement textarea() {
        return fluentWebDriver().textarea();
    }

    public FluentWebElement textarea(By by) {
        return fluentWebDriver().textarea(by);
    }

    public FluentWebElements textareas() {
        return fluentWebDriver().textareas();
    }

    public FluentWebElements textareas(By by) {
        return fluentWebDriver().textareas(by);
    }

    public FluentWebElement option() {
        return fluentWebDriver().option();
    }

    public FluentWebElement option(By by) {
        return fluentWebDriver().option(by);
    }

    public FluentWebElements options() {
        return fluentWebDriver().options();
    }

    public FluentWebElements options(By by) {
        return fluentWebDriver().options(by);
    }

    public FluentWebDriver within(Period period) {
        return fluentWebDriver().within(period);
    }

    public TestableString url() {
        return fluentWebDriver().url();
    }

    public TestableString title() {
        return fluentWebDriver().title();
    }
}
