package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.jbehave.web.selenium.GroovyGebFluentWebDriverPage

class Site extends GroovyGebFluentWebDriverPage {

  def Site(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def cartSize() {
      return $("#cart").text().toString().replace("Cart", "").trim()
  }

  def cartEmpty() {
      waitFor {
          cartSize().equals("")
      }
  }
}
