package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.jbehave.web.selenium.LazyWebDriver

class Home extends BasePage{

  def Home(LazyWebDriver lazyWebDriver) {
    super(lazyWebDriver)
  }

  def go() {
    get("http://www.etsy.com")
  }

  def go(String section) {
    go()
    findElement(By.xpath("//a[@title = '$section']")).click()
  }

  def search(String thing) {
    findElement(By.id("search-facet")).click()
    findElement(By.className("all")).click()
    findElement(By.id("search-query")).sendKeys(thing)
    findElement(By.id("search_submit")).click()
  }

  def goToBuySection() {
    findElement(By.linkText("Buy")).click()
  }
}
