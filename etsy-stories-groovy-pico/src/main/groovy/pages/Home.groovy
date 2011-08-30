package pages

import java.util.concurrent.TimeUnit
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
    findElement(By.xpath("//a[@title = '" + section + "']")).click()
  }

  def search(String thing) {
    manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
    $("#search-facet").click()
    $(".all").click()
    $("#search-query") << thing
    $("#search_submit").click()
  }

  def goToBuySection() {
    findElement(By.linkText("Buy")).click()
  }
}
