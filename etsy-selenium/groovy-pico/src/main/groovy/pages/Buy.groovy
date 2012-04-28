package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.jbehave.web.selenium.GroovyGebFluentWebDriverPage

class Buy extends GroovyGebFluentWebDriverPage {

  def Buy(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def selectTreasury() {
    $("#treasury-panel-button").click()
  }
}
