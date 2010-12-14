package pages

import org.openqa.selenium.By
import org.jbehave.web.selenium.WebDriverProvider

class Site extends BasePage{

  def Site(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def cartIsEmpty() {
    cartSize().shouldBe "0", "cart not empty"
  }

  def cartHasItems(String num) {
    cartSize().shouldBe num, "cart does not have $num elems"
  }

  def cartSize() {
    findElement(By.xpath("//li[@id = 'meta-cart']/a/em")).getText()
  }

}
