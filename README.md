# JBehave Selenium Tutorials on the Etsy.com website

Tutorials in using [jbehave-core](http://github.com/jbehave/jbehave-core), [jbehave-web](http://github.com/jbehave/jbehave-web), and [Selenium](http://seleniumhq.org/) 
against pre-existing website [etsy.com](http://etsy.com) to show how BDD allows the description and test of the behaviour of a web application. 

<img src="http://jbehave.org/reference/preview/images/jbehave-logo.png" alt="JBehave logo" align="right" />

## Modules

1. The module 'etsy-selenium' runs (via maven) stories verifying the behaviour of Etsy.com.  It uses Selenium to drive the web interaction and it has different sub-modules 
depending on the language and dependency injection framework used:  Groovy composed using Pico or Java composed using Spring. 
2. The module 'etsy-web-runner' is a simple webapp that allows generic stories to be run.  Note the app is not multiuser and not does support asynchronous 
execution.   It is not appropriate to run long-running stories.  It is also useful as a web front-end to the DSL syntax defined in your steps.

# Using Etsy.com

Etsy.com is a public live website that we treat as a black box and whose content we cannot control.  As the structure of the application evolves, 
some scenarios may fail because the underlying elements of the web pages are not found any more or the content categories have changed.

The objective of the tutorial is not to test the website itself but to show how its behaviour can be described in BDD terms and - as a consequence - 
be made verifiable in an automated way.  In this sense, failures are also a useful thing to have.   

That said, we strive to keep up with the structure of Etsy.com so if you find that some scenarios fail due to changes in Etsy.com, feel free to 
[contribute a patch](http://jbehave.org/how-to-contribute.html).

# JBehave Web Runner for Etsy.com

Uses the 'etsy-selenium/java-spring' steps and makes them available via the [JBehave Web Runner](http://jbehave.org/reference/web/stable/using-web-runner.html).

*NOTE:*  The Web Runner is a standalone web application that provides a simple web interface for running the Etsy stories via the Java steps.   
It does not run the (web) stories by command line.
 
# Building with Maven 

The tutorial modules can be built using the following Maven profiles: 

1. groovy-pico
2. java-spring (default)
3. web-runner

To run default profile using latest stable versions:

$ mvn clean install -Pstable

To run another profile using latest stable versions:

$ mvn clean install -P<profile>,stable

To run using latest snapshot from codehaus: 

$ mvn clean install -s settings.xml -P<profile>,codehaus

# Requirements 

Building the tutorial has been tested with Maven 3.0.5-3.2.1 and JDK 1.6-1.7.   Newer versions of Maven and JDK should work but could also present issues.  
If you find any, please report them via [JIRA](http://jbehave.org/reference/stable/issue-tracking.html)

# License

See [Licence](http://jbehave.org/license.html)
