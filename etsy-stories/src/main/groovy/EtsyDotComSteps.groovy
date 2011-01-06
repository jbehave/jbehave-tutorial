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

  AdvancedSearch advancedSearch
  Home home
  Site site
  SearchResults searchResults
  CartContents cartContents
  private String justBought = "";

  def EtsyDotComSteps() {
    GrooBe.activate()
  }

  @Given("I am shopping for a \$thing in \$section on Etsy.com")
  def shoppingForSomethingOnEtsyDotCom(String thing, String section) {
    home.go(section)
    home.search(thing)
  }

  @Given("I am searching on Etsy.com")
  def advancedSearchingOnEtsyDotCom() {
    advancedSearch.go()
  }

  @Given("that the cart is empty")
  def cartIsEmpty() {
    site.cartIsEmpty()
  }

  @Given("an item is in the cart")
  def anItemInTheEtsyCart() {
    shoppingForSomethingOnEtsyDotCom("hat", "Knitting")
    cartIsEmpty()
    putThingInCart("hat")
    cartNotEmpty("1")
  }

  @When("an item is added to the cart")
  def putThingInCart() {
    putThingInCart("hat")
  }

  @When("a \$thing is placed in the cart")
  def putThingInCart(String thing) {
    justBought = searchResults.buyFirst(thing)
  }

  @When("the item is removed")
  def removeItem() {
    cartContents.removeItem()
  }

  @When("I specify the \$subCat sub category")
  def knittingSubCategory(String subCat) {
    advancedSearch.subCategory(subCat)
  }

  @When("I search for \$thing")
  def seachForThing(String thing) {
    advancedSearch.searchFor(thing)
  }


  @Then("the cart contains that item")
  def cartHasThatItem() {
    cartContents.hasItem(justBought)
  }


  @Then("the cart has \$num items")
  @Alias("the cart has \$num item")
  def cartNotEmpty(String num) {
    site.cartHasItems(num)
  }

  @Then("the cart is empty")
  def thenCartIsEmpty() {
    cartIsEmpty()

  }

  @Then("there are search results")
  def thereAreSearchResults() {
    searchResults.someResults()
  }

}
