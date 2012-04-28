package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.jbehave.web.selenium.GroovyGebFluentWebDriverPage

class Treasury extends GroovyGebFluentWebDriverPage {

  def Treasury(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def chooseFirstGallery() {
      def element = findElement(By.xpath("//div[@class='item-treasury-info-box']/h3/a"))
      element.click()
      //$("div.item-treasury-info-box").find("h3").find("a").click()
  }
}
