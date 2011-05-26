# JBehave Tutorials

Using [jbehave-core](jbehave-core) and [jbehave-web](jbehave-web) against pre-existing website [etsy.com](http://etsy.com)

<img src="http://jbehave.org/reference/preview/images/jbehave-logo.png" alt="JBehave logo" align="right" />

## Modules

1. The module 'etsy-stories-groovy-pico' runs (via mvn) stories using steps written in Groovy and composed using Pico. 
2. The module 'etsy-stories-java-spring' runs (via mvn) stories using steps written in Java and composed using Spring. 
3. The module 'etsy-web-runner' is a simple webapp that allows generic stories to be run.  Note the app is not multiuser and not does support asynchronous 
execution.   It is not appropriate to run long-running stories.  It is also useful as a web front-end to the DSL syntax defined in your steps.

## License

See LICENSE.txt in the source root (BSD).