package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class Site extends BasePage{

  def Site(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }


  def cartSize() {
      def cartSizeDivLocator = By.xpath("//div[@class='count']")
      try {
          def cartSizeDiv = findElement(cartSizeDivLocator)
          return cartSizeDiv.text
      } catch (org.openqa.selenium.NoSuchElementException e) {
          return ""
      }

  }
}
