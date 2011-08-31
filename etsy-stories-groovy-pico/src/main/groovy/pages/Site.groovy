package pages

import org.jbehave.web.selenium.WebDriverProvider

class Site extends BasePage{

  def Site(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def cartSize() {
      return $("#cart").text().replace("Cart", "").trim()
  }

  def cartEmpty() {
      waitFor {
          cartSize().equals("")
      }
  }
}
