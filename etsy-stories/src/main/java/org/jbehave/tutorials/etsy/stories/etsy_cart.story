Meta:
@category basic

Scenario: Place item in cart

Given I am shopping for a hat in Knitting on Etsy.com
And the cart is empty
When a hat is placed in the cart
Then the cart has 1 item

Scenario: Remove item from cart

Given an Item in an Etsy.com shopping cart
When the item is removed
Then the cart is empty