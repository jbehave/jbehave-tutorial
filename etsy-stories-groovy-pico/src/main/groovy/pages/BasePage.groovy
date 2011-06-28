package pages;


import com.github.tanob.groobe.GrooBe
import geb.Browser
import org.jbehave.web.selenium.WebDriverPage
import org.jbehave.web.selenium.WebDriverProvider

public class BasePage extends WebDriverPage {

    public BasePage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
        GrooBe.activate()
    }

    def methodMissing(String name, args) {
        getBrowser()."$name"(* args)
    }

    private browser

    private Browser getBrowser() {
        if (browser == null) {
            browser = new Browser()
            browser.setDriver(webDriver())
        }
        browser
    }

}
