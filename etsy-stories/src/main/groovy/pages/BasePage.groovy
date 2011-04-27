package pages;

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.LazyWebDriver
import org.jbehave.web.selenium.LazyWebDriverPage

public class BasePage extends LazyWebDriverPage {

  public BasePage(LazyWebDriver lazyWebDriver) {
    super(lazyWebDriver)
    GrooBe.activate()
  }
}
