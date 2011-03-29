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

TODO


