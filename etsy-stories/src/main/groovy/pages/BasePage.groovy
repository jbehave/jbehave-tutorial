package pages;

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverPage
import org.jbehave.web.selenium.WebDriverProvider
import org.jbehave.web.selenium.LazyWebDriver

public class BasePage extends WebDriverPage {

  public BasePage(LazyWebDriver lazyWebDriver) {
    super(lazyWebDriver)
    GrooBe.activate()
  }
}
