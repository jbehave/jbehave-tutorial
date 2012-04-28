# JBehave Selenium Tutorials on the Etsy.com website

Using [jbehave-core](http://github.com/jbehave/jbehave-core), [jbehave-web](http://github.com/jbehave/jbehave-web), and [Selenium](http://seleniumhq.org/) against pre-existing website [etsy.com](http://etsy.com) to test it's shopping cart.

<img src="http://jbehave.org/reference/preview/images/jbehave-logo.png" alt="JBehave logo" align="right" />

## Modules

1. The module 'etsy-selenium' runs (via maven) stories verifying the behaviour of Etsy.com.  It uses Selenium to drive the web interaction and it has different sub-modules 
depending on the language and dependency injection framework used:  Groovy composed using Pico or Java composed using Spring. 
2. The module 'etsy-web-runner' is a simple webapp that allows generic stories to be run.  Note the app is not multiuser and not does support asynchronous 
execution.   It is not appropriate to run long-running stories.  It is also useful as a web front-end to the DSL syntax defined in your steps.

## License

See LICENSE.txt in the source root (BSD).
