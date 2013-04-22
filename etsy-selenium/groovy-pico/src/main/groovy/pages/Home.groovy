package pages

import java.util.concurrent.TimeUnit
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.jbehave.web.selenium.GroovyGebFluentWebDriverPage

class Home extends GroovyGebFluentWebDriverPage {

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
    $("#search-query") << thing
    $("#search_submit").click()
  }

  def goToBuySection() {
    findElement(By.linkText("Etsy")).click()
  }
}
