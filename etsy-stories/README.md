# JBehave Tutorial

JBehave is a BDD framework for Java and Groovy. Refer the [jbehave-core](jbehave-core) and [jbehave-web](jbehave-web) sister projects.

This tutorial uses JBehave and Selenium2 to test [Etsy.com](http://etsy.com) (an live online shopping site).

<img src="http://jbehave.org/reference/preview/images/jbehave-logo.png" alt="JBehave logo" align="right" />

## Two Modules

1. The first module 'etsy-stories' is the actual tests that will run, with browsers flickering and reports that are a result.
2. The second module 'etsy-web-runner' is an online app that allows ad-hoc stories to be run.  Note the app is not multiuser and will appear to hang for the duration of the story run in question.

## Running the tests

Using Maven (installed on your system), do:

    mvn clean install

You should see Firefox (installed on your system) flicker as it tests Etsy.com

## Seeing the reports

Inside a directory etsy-stories/target/jbehave/view/ there is a navigator.html page that can be loaded locally.  There should be three rows; One for each story.  The rows are double-clickable to see the details of the story run.

## Using this tutorial to start your own JBehave based tests for a web-site.

While a lot of the directory structure of this tutorial will be good for you too, there are bits that are not so useful.

1) All three .story files in src/main/resources/stories/ are good for inspiration, but should not be copied into your project.
2) src/main/java/org/jbehave/tutorials/etsy/EtsyDotComStories.java should at least be renamed before consuming into your new project
3) The Groovy page-object classes in src/main/groovy/pages/ are good to look at only. The exception would be that BasePage might be useful in your project.
4) The src/main/groovy/housekeeping directory contains a steps class that clears the cart between scenarios.  You may or may not need that.
5) The src/main/groovy/EtsyDotComSteps.groovy source is again good for inspiration only.

The resulting JBehave output also contains some stuff from a related project.  The navigator.html output is actually from http://paul-hammant.github.com/StoryNavigator/downloads.html and unzipped into src/main/storynavigator/ so that it can be customized if needed.  Get a fresh copy, rather than use the one checked into the JBehave tutorial.



