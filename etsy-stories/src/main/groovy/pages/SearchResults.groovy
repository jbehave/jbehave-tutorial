package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class SearchResults extends BasePage{

  def SearchResults(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }


  def buyFirst(String thing) {
    List elems = getElems()
    for (int i = 0; i < elems.size(); i++) {
      def elem = elems.get(i)
      def title = elem.getAttribute("title")
      if (title.toLowerCase().contains(thing.toLowerCase())) {
        elem.click()
        def ix = getCurrentUrl().indexOf("/listing/") + 9
        def id = getCurrentUrl().substring(ix, ix + 8)
        id.isNumber().shouldBe true, "no listing found"
        def buyButton = findElements(By.xpath("//input[@value = 'Add to Cart']")).get(0)
        buyButton.click()
        return id
      }
    }
    "<not-bought>"
  }

  public getElems() {
    findElements(By.xpath("//a[@class = 'listing-thumb']"))
  }
}
