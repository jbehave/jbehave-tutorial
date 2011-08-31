package pages

import org.jbehave.web.selenium.WebDriverProvider

class CartContents extends BasePage {

  Site site

  def CartContents(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def hasItem(String item) {
    go "http://www.etsy.com/cart"
    try {
      $("div#listing-$item")
    } catch (ex) {
      return false // could also be that html has changed
    }
    return true
  }

  def removeItem() {
    go "http://www.etsy.com/cart"
    $("a", rel: 'remove').click()
  }
}
