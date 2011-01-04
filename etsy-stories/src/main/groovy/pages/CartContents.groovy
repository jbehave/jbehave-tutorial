package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class CartContents extends BasePage{

  def CartContents(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def removeItem() {
    get("http://www.etsy.com/cartcheckout.php")
    findElement(By.xpath("//a[@rel = 'remove']")).click()
  }
}
