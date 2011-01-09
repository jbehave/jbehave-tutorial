package pages;

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverPage
import org.jbehave.web.selenium.WebDriverProvider

public class BasePage extends WebDriverPage {

  public BasePage(WebDriverProvider driverProvider) {
    super(driverProvider)
    GrooBe.activate()
  }
}
