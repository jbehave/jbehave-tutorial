package pages;

import org.jbehave.web.selenium.WebDriverProvider;

public class PageFactory {

  private WebDriverProvider driverProvider

  public PageFactory(WebDriverProvider driverProvider) {
    this.driverProvider = driverProvider
  }

  def getDriverProvider() {
    return driverProvider
  }

}
