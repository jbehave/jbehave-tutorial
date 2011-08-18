package org.jbehave.tutorials.etsy.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriverImpl;
import org.seleniumhq.selenium.fluent.OngoingMultipleElements;
import org.seleniumhq.selenium.fluent.OngoingSingleElement;

public class FluentWebDriverPage extends WebDriverPage implements FluentWebDriver {

    public FluentWebDriverPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    private FluentWebDriverImpl fluentWebDriver() {
        return new FluentWebDriverImpl(webDriver());
    }
    
    public OngoingSingleElement span() {
        return fluentWebDriver().span();
    }

    public OngoingSingleElement span(By by) {
        return fluentWebDriver().span(by);
    }

    public OngoingMultipleElements spans() {
        return fluentWebDriver().spans();
    }

    public OngoingMultipleElements spans(By by) {
        return fluentWebDriver().spans(by);
    }

    public OngoingSingleElement div() {
        return fluentWebDriver().div();
    }

    public OngoingSingleElement div(By by) {
        return fluentWebDriver().div(by);
    }

    public OngoingMultipleElements divs() {
        return fluentWebDriver().divs();
    }

    public OngoingSingleElement divs(By by) {
        return fluentWebDriver().div(by);
    }

    public OngoingSingleElement button() {
        return fluentWebDriver().button();
    }

    public OngoingSingleElement button(By by) {
        return fluentWebDriver().button(by);
    }

    public OngoingMultipleElements buttons() {
        return fluentWebDriver().buttons();
    }

    public OngoingSingleElement buttons(By by) {
        return fluentWebDriver().button(by);
    }

    public OngoingSingleElement link() {
        return fluentWebDriver().link();
    }

    public OngoingSingleElement link(By by) {
        return fluentWebDriver().link(by);
    }

    public OngoingMultipleElements links() {
        return fluentWebDriver().links();
    }

    public OngoingMultipleElements links(By by) {
        return fluentWebDriver().links(by);
    }

    public OngoingSingleElement input() {
        return fluentWebDriver().input();
    }

    public OngoingSingleElement input(By by) {
        return fluentWebDriver().input(by);
    }

    public OngoingMultipleElements inputs() {
        return fluentWebDriver().inputs();
    }

    public OngoingMultipleElements inputs(By by) {
        return fluentWebDriver().inputs(by);
    }

    public OngoingSingleElement select() {
        return fluentWebDriver().select();
    }

    public OngoingSingleElement select(By by) {
        return fluentWebDriver().select(by);
    }

    public OngoingMultipleElements selects() {
        return fluentWebDriver().selects();
    }

    public OngoingMultipleElements selects(By by) {
        return fluentWebDriver().selects(by);
    }

    public OngoingSingleElement li() {
        return fluentWebDriver().li();
    }

    public OngoingSingleElement li(By by) {
        return fluentWebDriver().li(by);
    }

    public OngoingMultipleElements lis() {
        return fluentWebDriver().lis();
    }

    public OngoingMultipleElements lis(By by) {
        return fluentWebDriver().lis(by);
    }

    public OngoingSingleElement h1() {
        return fluentWebDriver().h1();
    }

    public OngoingSingleElement h1(By by) {
        return fluentWebDriver().h1(by);
    }

    public OngoingMultipleElements h1s() {
        return fluentWebDriver().h1s();
    }

    public OngoingMultipleElements h1s(By by) {
        return fluentWebDriver().h1s(by);
    }

    public OngoingSingleElement h2() {
        return fluentWebDriver().h2();
    }

    public OngoingSingleElement h2(By by) {
        return fluentWebDriver().h2(by);
    }

    public OngoingMultipleElements h2s() {
        return fluentWebDriver().h2s();
    }

    public OngoingMultipleElements h2s(By by) {
        return fluentWebDriver().h2s(by);
    }

    public OngoingSingleElement h3() {
        return fluentWebDriver().h3();
    }

    public OngoingSingleElement h3(By by) {
        return fluentWebDriver().h3(by);
    }

    public OngoingMultipleElements h3s() {
        return fluentWebDriver().h3s();
    }

    public OngoingMultipleElements h3s(By by) {
        return fluentWebDriver().h3s(by);
    }

    public OngoingSingleElement h4() {
        return fluentWebDriver().h4();
    }

    public OngoingSingleElement h4(By by) {
        return fluentWebDriver().h4(by);
    }

    public OngoingMultipleElements h4s() {
        return fluentWebDriver().h4s();
    }

    public OngoingMultipleElements h4s(By by) {
        return fluentWebDriver().h4s(by);
    }

    public OngoingSingleElement p() {
        return fluentWebDriver().p();
    }

    public OngoingSingleElement p(By by) {
        return fluentWebDriver().p(by);
    }

    public OngoingMultipleElements ps() {
        return fluentWebDriver().ps();
    }

    public OngoingMultipleElements ps(By by) {
        return fluentWebDriver().ps(by);
    }

    public OngoingSingleElement img() {
        return fluentWebDriver().img();
    }

    public OngoingSingleElement img(By by) {
        return fluentWebDriver().img(by);
    }

    public OngoingMultipleElements imgs() {
        return fluentWebDriver().imgs();
    }

    public OngoingMultipleElements imgs(By by) {
        return fluentWebDriver().imgs(by);
    }

    public OngoingSingleElement table() {
        return fluentWebDriver().table();
    }

    public OngoingSingleElement table(By by) {
        return fluentWebDriver().table(by);
    }

    public OngoingMultipleElements tables() {
        return fluentWebDriver().tables();
    }

    public OngoingMultipleElements tables(By by) {
        return fluentWebDriver().tables(by);
    }

    public OngoingSingleElement tr() {
        return fluentWebDriver().tr();
    }

    public OngoingSingleElement tr(By by) {
        return fluentWebDriver().tr(by);
    }

    public OngoingMultipleElements trs() {
        return fluentWebDriver().trs();
    }

    public OngoingMultipleElements trs(By by) {
        return fluentWebDriver().trs(by);
    }

    public OngoingSingleElement td() {
        return fluentWebDriver().td();
    }

    public OngoingSingleElement td(By by) {
        return fluentWebDriver().td(by);
    }

    public OngoingMultipleElements tds() {
        return fluentWebDriver().tds();
    }

    public OngoingMultipleElements tds(By by) {
        return fluentWebDriver().tds(by);
    }

    public OngoingSingleElement th() {
        return fluentWebDriver().th();
    }

    public OngoingSingleElement th(By by) {
        return fluentWebDriver().th(by);
    }

    public OngoingMultipleElements ths() {
        return fluentWebDriver().ths();
    }

    public OngoingMultipleElements ths(By by) {
        return fluentWebDriver().ths(by);
    }

    public OngoingSingleElement ul() {
        return fluentWebDriver().ul();
    }

    public OngoingSingleElement ul(By by) {
        return fluentWebDriver().ul(by);
    }

    public OngoingMultipleElements uls() {
        return fluentWebDriver().uls();
    }

    public OngoingMultipleElements uls(By by) {
        return fluentWebDriver().uls(by);
    }

    public OngoingSingleElement ol() {
        return fluentWebDriver().ol();
    }

    public OngoingSingleElement ol(By by) {
        return fluentWebDriver().ol(by);
    }

    public OngoingMultipleElements ols() {
        return fluentWebDriver().ols();
    }

    public OngoingMultipleElements ols(By by) {
        return fluentWebDriver().ols(by);
    }

    public OngoingSingleElement form() {
        return fluentWebDriver().form();
    }

    public OngoingSingleElement form(By by) {
        return fluentWebDriver().form(by);
    }

    public OngoingMultipleElements forms() {
        return fluentWebDriver().forms();
    }

    public OngoingMultipleElements forms(By by) {
        return fluentWebDriver().forms(by);
    }

    public OngoingSingleElement textarea() {
        return fluentWebDriver().textarea();
    }

    public OngoingSingleElement textarea(By by) {
        return fluentWebDriver().textarea(by);
    }

    public OngoingMultipleElements textareas() {
        return fluentWebDriver().textareas();
    }

    public OngoingMultipleElements textareas(By by) {
        return fluentWebDriver().textareas(by);
    }

    public OngoingSingleElement option() {
        return fluentWebDriver().option();
    }

    public OngoingSingleElement option(By by) {
        return fluentWebDriver().option(by);
    }

    public OngoingMultipleElements options() {
        return fluentWebDriver().options();
    }

    public OngoingMultipleElements options(By by) {
        return fluentWebDriver().options(by);
    }
}
