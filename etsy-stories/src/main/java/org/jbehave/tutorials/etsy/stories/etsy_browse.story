Narrative: 

In order to show the browsing cart functionality
As a user
I want to browse in a gallery

Meta:
@category browsing

Scenario: Browsing around the site for items

Given I am on etsy.com
When I want to browse through a treasury gallery
!-- We don't care for the results, just the gallery
Then results will be displayed in the gallery