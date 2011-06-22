import org.jbehave.core.annotations.BeforeScenario
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebDriverException

class HousekeepingSteps {

  WebDriverProvider webDriverProvider;

  @BeforeScenario
  def emptyCart() {
    try {
      webDriverProvider.get().manage().deleteCookieNamed("uaid")
    } catch (WebDriverException e) {
      // tis OK
    }
  }
  


}
