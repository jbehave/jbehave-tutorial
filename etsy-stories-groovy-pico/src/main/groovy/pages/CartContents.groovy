package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class CartContents extends BasePage {

  Site site

  def CartContents(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def hasItem(String item) {
    get("http://www.etsy.com/cart")
    try {
      findElement(By.id("listing-$item-ship"))
    } catch (ex) {
      return false
    }
    return true
  }

  def removeItem() {
    get("http://www.etsy.com/cart")
    findElement(By.xpath("//a[@rel = 'remove']")).click()
    while (!site.cartSize().equals("")) {
      sleep(200)
    }

  }
}
