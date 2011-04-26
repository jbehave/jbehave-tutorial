package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.jbehave.web.selenium.LazyWebDriver

class Treasury extends BasePage{

  def Treasury(LazyWebDriver lazyWebDriver) {
    super(lazyWebDriver)
  }

  def chooseFirstGallery() {
      def element = findElement(By.xpath("//div[@class='item-treasury-info-box']/h3/a"))
      element.click()
  }
}
