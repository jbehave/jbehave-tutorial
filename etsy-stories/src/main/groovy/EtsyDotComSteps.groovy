import com.github.tanob.groobe.GrooBe
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import pages.AdvancedSearch
import pages.CartContents
import pages.Home
import pages.SearchResults
import pages.Site
import org.jbehave.core.annotations.Composite
import pages.Buy
import pages.Treasury
import static org.junit.Assert.*

public class EtsyDotComSteps {

  AdvancedSearch advancedSearch
  Home home
  Site site
  SearchResults searchResults
  CartContents cartContents
  Buy buy
  Treasury treasury

  private String justBought = ""

  def EtsyDotComSteps() {
    GrooBe.activate()
  }

  @Given("I am shopping for a \$thing in \$section on Etsy.com")
  def shoppingForSomethingOnEtsyDotCom(String thing, String section) {
    home.go(section)
    home.search(thing)
  }

  @Given("I am on etsy.com")
  def homepageOnEtsyDotCom() {
    home.go()
  }

  @Given("I am searching on Etsy.com")
  def advancedSearchingOnEtsyDotCom() {
    advancedSearch.go()
  }

  @Given("that the cart is empty")
  @Then("the cart will be empty")
  def cartIsEmpty() {
    site.cartSize().shouldBe("0", "cart not empty")
  }

  @Given("the cart contains one item")
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

  @When("I search for an item")
  def searchForItem(){
    home.search("hat")
  }

  @When("I want to browse through a treasury gallery")
  @Composite(steps = [
          "When I want to buy something from etsy.com",
          "When I want to browse the treasury",
          "When I choose the first treasury gallery"
  ])
  def browseToFirstTreasuryGallery() {}

  @When("I want to buy something from etsy.com")
  def selectBuyTabAtTop(){
    home.goToBuySection()
  }

  @When("I want to browse the treasury")
  def browseTreasury(){
    buy.selectTreasury()
  }

  @When("I choose the first treasury gallery")
  def selectFirstTreasuryGallery(){
    treasury.chooseFirstGallery()
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
    assertTrue cartContents.hasItem(justBought)
  }

  @Then("the cart has \$num items")
  @Alias("the cart has \$num item")
  def cartNotEmpty(String num) {
    site.cartSize().shouldBe(num, "cart does not have $num elems")
  }

  @Then("there are search results")
  @Alias("results will be displayed in the gallery")
  def thereAreSearchResults() {
    searchResults.getElems().shouldNotBe 0
  }
}
