package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select

class AdvancedSearch extends BasePage{

  def AdvancedSearch(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def go() {
    get("http://www.etsy.com/search_advanced.php")
  }

  def go(String section) {
    go()
    findElement(By.xpath("//a[@title = '$section']")).click()
  }

  def search(String thing) {
    findElement(By.id("search-query")).sendKeys(thing)
    findElement(By.id("search_submit")).click()
  }

  def subCategory(String subCategory) {
    def select = findElement(By.xpath("//select[@class = 'handmade']"))
    new Select(select).selectByValue(subCategory.toLowerCase())
  }

  def searchFor(String thing) {
    def field = findElement(By.id("search_query"))
    field.sendKeys thing
    field.submit()
  }
}
