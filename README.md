# JBehave WebDriver Tutorial

Running JBehave and WebDriver against pre-existing website [etsy.com](http://etsy.com)

<img src="http://jbehave.org/reference/preview/images/jbehave-logo.png" alt="JBehave logo" align="right" />

## Running

Firstly, cd into etsy-stories

This will run the build and (after a minute or so) Firefox will open and test the etsy.com website:

    mvn install 

This will run a single story (one contained in a etsy_cart.story file):

    mvn install -DstoryFilter=etsy_cart

This will run a suite based on the meta filters in the three story files:

    mvn install -Dmeta.filter="+color red"

This will run tests in parallel in SauceLabs' stack:

(use YOUR details from YOUR [SauceLabs.com](http://saucelabs.com) account)

    mvn install -DSAUCE_USERNAME=your_sauce_id -DSAUCE_ACCESS_KEY=your_sauce_access_key

## Viewing the results

In a directory etsy-stories/target/view/navigator.html

## Related

See also the [jbehave-web](jbehave-web) sister project for web extensions to JBehave, and [jbehave-tutorial](jbehave-tutorial) for a decent example of JBehave testing of a web application.

## License

See LICENSE.txt in the source root (BSD).