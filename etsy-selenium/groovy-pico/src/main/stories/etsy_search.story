Search Etsy.com by sub category

Meta:
@category advanced
@color red

Narrative: 

In order to show the advance cart functionality
As a user
I want to search for an item in a sub category

Scenario: Advanced Search for a hat

GivenStories: etsy_given.story

Given I am searching on Etsy.com
When I specify the Vintage sub category
And I search for hat
Then there are search results

Scenario: Advanced Search for a ring

Given I am searching on Etsy.com
When I specify the Jewelry sub category
And I search for ring
Then there are search results
