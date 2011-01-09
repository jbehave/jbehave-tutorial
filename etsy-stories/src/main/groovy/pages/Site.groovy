package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class Site extends BasePage{

  def Site(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def cartSize() {
    findElement(By.xpath("//li[@id = 'meta-cart']/a/em")).getText()
  }
}
