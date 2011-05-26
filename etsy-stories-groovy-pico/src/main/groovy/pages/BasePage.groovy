package pages;

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverPage
import org.jbehave.web.selenium.WebDriverProvider

public class BasePage extends WebDriverPage {

  public BasePage(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
    GrooBe.activate()
  }
}
