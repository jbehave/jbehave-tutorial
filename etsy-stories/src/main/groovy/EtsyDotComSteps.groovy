import com.github.tanob.groobe.GrooBe
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.jbehave.web.selenium.WebDriverProvider
import pages.AdvancedSearch
import pages.CartContents
import pages.Home
import pages.SearchResults
import pages.Site

public class EtsyDotComSteps {

  WebDriverProvider webDriverProvider;

  private AdvancedSearch advancedSearch

  def EtsyDotComSteps() {
    GrooBe.activate()
  }

  @Given("I am shopping for a \$thing in \$section on Etsy.com")
  def shoppingForSomethingOnEtsyDotCom(String thing, String section) {
    def home = new Home(getWebDriverProvider())
    home.go(section)
    home.search(thing)
  }

  @Given("I am searching on Etsy.com")
  def advancedSearchingOnEtsyDotCom() {
    advancedSearch = new AdvancedSearch(getWebDriverProvider())
    advancedSearch.go()
  }

  @Given("the cart is empty")
  def cartIsEmpty() {
    new Site(getWebDriverProvider()).cartIsEmpty()
  }

  @Given("an Item in an Etsy.com shopping cart")
  def anItemInTheEtsyCart() {
    shoppingForSomethingOnEtsyDotCom("hat", "Knitting")
    cartIsEmpty()
    putThingInCart("hat")
    cartNotEmpty()
  }

  @When("a \$thing is placed in the cart")
  def putThingInCart(String thing) {
    new SearchResults(getWebDriverProvider()).buyFirst(thing)
  }

  @When("the item is removed")
  def removeItem() {
    new CartContents(getWebDriverProvider()).removeItem()
  }

  @When("I specify the \$subCat sub category")
  def knittingSubCategory(String subCat) {
    advancedSearch.subCategory(subCat)
  }

  @When("I search for \$thing")
  def seachForThing(String thing) {
    advancedSearch.searchFor(thing)
  }


  @Then("the cart has \$num items")
  @Alias("the cart has \$num item")
  def cartNotEmpty() {
    new Site(getWebDriverProvider()).cartHasItems("1")
  }

  @Then("the cart is empty")
  def cartIsEmpty2() {
    cartIsEmpty()

  }

  @Then("there are search results")
  def thereAreSearchResults() {
    new SearchResults(webDriverProvider).someResults()
  }

}
