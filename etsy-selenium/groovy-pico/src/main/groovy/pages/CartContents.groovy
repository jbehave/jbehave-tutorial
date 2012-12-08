package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.jbehave.web.selenium.GroovyGebFluentWebDriverPage
import org.openqa.selenium.By

import static org.openqa.selenium.By.id;

class CartContents extends GroovyGebFluentWebDriverPage {

  Site site

  def CartContents(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def hasItem(String item) {
    go "http://www.etsy.com/cart"
    try {
      div(id("listing-" + item))
    } catch (ex) {
      return false // could also be that html has changed
    }
    return true
  }

  def removeItem() {
    go "http://www.etsy.com/cart"
    findElement(By.cssSelector("li.action-remove")).findElement(By.tagName("a")).click()
  }
}
