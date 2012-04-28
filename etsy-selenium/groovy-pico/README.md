# Etsy.com using Groovy and Pico

This tutorial uses JBehave 3.x and Selenium 2.x to test [Etsy.com](http://etsy.com) (an live online shopping site).

<img src="http://jbehave.org/reference/preview/images/jbehave-logo.png" alt="JBehave logo" align="right" />

## Running the stories

This will run the build and (after a minute or so) Firefox will open and test the etsy.com website:

    mvn install 

You should see Firefox (installed on your system) flicker as it tests Etsy.com

This will run a single story (one contained in a etsy_cart.story file):

    mvn install -DstoryFilter=etsy_cart

This will run a suite based on the meta filters in the three story files:

    mvn install -Dmeta.filter="+color red"

This will run tests in parallel in SauceLabs' stack:

(use YOUR details from YOUR [SauceLabs.com](http://saucelabs.com) account)

    mvn install -DSAUCE_USERNAME=your_sauce_id -DSAUCE_ACCESS_KEY=your_sauce_access_key

## Viewing the results

In a directory target/view, a page named 'navigator.html' has been generated.  If you open that in Firefox, Safari or Internet Explorer (but not Chrome), you can see the three stories that have run and their completion status.

There should be a row for each story.  The rows are double-clickable to see the details of the story run.

## Using this tutorial to start your own JBehave-based integration tests for a web site.

The tutorial aims to provide a fully-functional project that you can use to model you own project:

1. src/main/java/org/jbehave/tutorials/etsy/EtsyDotComStories.java is the entry-point that JBehave uses to run the stories.
2. src/main/stories contains the stories run by JBehave via EtsyDotComStories.java.
3. src/main/groovy/HousekeepingSteps.groovy contains the steps does housekeeping chores, such as emptying cart between scenarios. 
4. src/main/groovy/EtsyDotComSteps.groovy contains the steps mapped to the textual steps.
5. src/main/groovy/pages contains the Groovy page-objects used by steps to abstract in a more manageable and maintainable way the interaction with the web pages via Selenium WebDriver.

The resulting JBehave output also contains some stuff from a related project.  
The navigator.html output is actually from http://paul-hammant.github.com/StoryNavigator/downloads.html and unzipped into src/main/storynavigator/ so that it can be customized if needed.  Get a fresh copy, rather than use the one checked into the JBehave tutorial.



