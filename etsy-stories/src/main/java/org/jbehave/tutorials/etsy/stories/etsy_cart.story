Meta:
@category basic

Scenario: Item can be added to cart

Given that the cart is empty
When an item is added to the cart
Then the cart contains that item

Scenario: Item can be removed from cart

Given the cart contains one item
When the item is removed
Then the cart will be empty