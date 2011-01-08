package org.jbehave.tutorials.etsy.webrunner;


import groovy.lang.GroovyCodeSource;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.groovy.BytecodeGroovyClassLoader;
import org.jbehave.core.configuration.groovy.GroovyContext;
import org.jbehave.core.configuration.groovy.GroovyResourceFinder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.Steps;
import org.jbehave.core.steps.groovy.GroovyStepsFactory;
import org.jbehave.web.runner.wicket.WebRunnerApplication;
import org.jbehave.web.selenium.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

import static org.jbehave.tutorials.etsy.EtsyDotComStories.makeConfiguration;
import static org.jbehave.tutorials.etsy.EtsyDotComStories.makeGroovyCandidateSteps;

public class EtsyDotComStoriesWebApplication extends WebRunnerApplication {

    private final WebDriverProvider driverProvider = new TypeWebDriverProvider();
    private static final BytecodeGroovyClassLoader classLoader =new BytecodeGroovyClassLoader();
    private Configuration configuration;

    protected List<CandidateSteps> candidateSteps() {
        List<CandidateSteps> steps = makeGroovyCandidateSteps(configuration(), new GroovyResourceFinder(), driverProvider);
        steps.add(0, stepify(new PerStoriesWebDriverSteps(driverProvider))); // before other Groovy steps
        steps.add(stepify(new WebDriverScreenshotOnFailure(driverProvider)));
        return steps;
    }

    protected Configuration configuration() {
        configuration = makeConfiguration(this.getClass(), driverProvider);
        return configuration;
    }

    private Steps stepify(final Object steps) {
        return new Steps(configuration, steps);
    }


}
