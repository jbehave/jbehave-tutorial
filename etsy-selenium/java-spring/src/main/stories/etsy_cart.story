ShoppingCart functionality for Etsy.com

Meta:
@category basic
@color blue

Narrative: 

In order to show the basic cart functionality
As a user
I want to add and remove items from the cart

Scenario: Item can be added to cart

Given that the cart is empty
!-- We don't care for which item is added to the cart
When I search for an item
And an item is added to the cart
Then the cart contains that item
