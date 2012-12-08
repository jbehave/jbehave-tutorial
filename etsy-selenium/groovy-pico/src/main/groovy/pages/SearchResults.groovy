package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.jbehave.web.selenium.GroovyGebFluentWebDriverPage

class SearchResults extends GroovyGebFluentWebDriverPage {

  def SearchResults(WebDriverProvider webDriverProvider) {
    super(webDriverProvider)
  }

  def buyFirst(String thing) {
    List elements = getElems()
    for (int i = 0; i < elements.size(); i++) {
      def elem = elements.get(i)
      def title = elem.getAttribute("title")
      if (title.toLowerCase().contains(thing.toLowerCase())) {
        // traverse into detail
        elem.click()
        def ix = getCurrentUrl().indexOf("/listing/") + 9
        def id = getCurrentUrl().substring(ix, ix + 9)
        id.isNumber().shouldBe true, "no listing found"
        def buyButton = findElement(By.xpath("//input[@value = 'Add to Cart']"))
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
