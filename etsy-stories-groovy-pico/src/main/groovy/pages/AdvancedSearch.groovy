package pages

import org.jbehave.web.selenium.WebDriverProvider
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
    $("a", title : section).click()
  }

  def search(String thing) {
    $("#search-query").sendKeys(thing)
    $("#search_submit").click()
  }

  def subCategory(String subCategory) {
    new Select($("select.handmade").getElement(0)).selectByValue(subCategory.toLowerCase())
  }

  def searchFor(String thing) {
    def field = $("#search_query")
    field << thing
    field.submit()
  }
}
