package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class Home extends BasePage{

  def Home(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def go() {
    get("http://www.etsy.com")
  }

  def go(String section) {
    go()
    try {
      findElement(By.xpath("//a[@title = '$section']")).click()
    } catch (Throwable t) {
      Thread.currentThread().sleep(10000)
    }
  }

  def search(String thing) {
    findElement(By.id("search-query")).sendKeys(thing)
    findElement(By.id("search_submit")).click()
  }

  def goToBuySection() {
    findElement(By.linkText("Buy")).click()
  }
}
