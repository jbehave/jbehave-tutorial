package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class Site extends BasePage{

  def Site(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def cartSize() {
      findElement(By.xpath("//div[@id='cart']/a/div[2]")).text
  }
}
