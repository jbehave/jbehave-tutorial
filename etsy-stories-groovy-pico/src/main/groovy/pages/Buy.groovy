package pages

import org.jbehave.web.selenium.WebDriverProvider

class Buy extends BasePage{

  def Buy(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def selectTreasury() {
    $("#treasury-panel-button").click()
  }
}
