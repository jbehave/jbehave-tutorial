package pages

import org.jbehave.web.selenium.WebDriverProvider

class Site extends BasePage{

  def Site(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def cartSize() {
      def text = $("#cart").text()
      return text.replace("Cart", "").trim()
  }
}
