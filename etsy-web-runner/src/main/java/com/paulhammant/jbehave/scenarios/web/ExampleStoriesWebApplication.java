package com.paulhammant.jbehave.scenarios.web;

import groovy.lang.GroovyCodeSource;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.groovy.BytecodeGroovyClassLoader;
import org.jbehave.core.configuration.groovy.GroovyContext;
import org.jbehave.core.configuration.groovy.GroovyResourceFinder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.Steps;
import org.jbehave.core.steps.groovy.GroovyStepsFactory;
import org.jbehave.web.runner.wicket.WebRunnerApplication;
import org.jbehave.web.selenium.PerScenarioWebDriverSteps;
import org.jbehave.web.selenium.TypeWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

import static org.jbehave.tutorials.etsy.EtsyDotComStories.makeConfiguration;

public class ExampleStoriesWebApplication extends WebRunnerApplication {

    private final WebDriverProvider driverProvider = new TypeWebDriverProvider();
    private static final BytecodeGroovyClassLoader classLoader =new BytecodeGroovyClassLoader();
    private static URL url;
    static {
        try {
            url = new File("target/jbehave-example-scenarios-webapp-1.0-SNAPSHOT/WEB-INF/classes").getCanonicalFile().toURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected List<CandidateSteps> candidateSteps() {
        Configuration configuration = makeConfiguration(this.getClass(), driverProvider);
        GroovyContext context = new GroovyContext(new GroovyResourceFinder(url, "**/*.groovy", "")) {
            @Override
            public Object newInstance(String resource) {
                    String spec = url + resource;
                GroovyCodeSource source = null;
                try {
                    source = new GroovyCodeSource(new URL(spec));
                } catch (IOException e) {
                    throw new UnsupportedOperationException("couldn't find source: " + spec);
                }
                Class<?> parsedClass = (Class<?>) classLoader.parseClass(source, true);

                    try {
                        Object inst = parsedClass.newInstance();
                        try {
                            Method declaredMethod = inst.getClass().getDeclaredMethod("setWebDriverProvider", WebDriverProvider.class);
                            declaredMethod.invoke(inst, driverProvider);
                        } catch (NoSuchMethodException e) {
                            // fine, it does not need a WebDriverProvider via setter.
                        }
                        return inst;
                    } catch (IllegalAccessException e) {
                        return ""; // not a steps class, discard for the sake of steps registration
                    } catch (InvocationTargetException e) {
                        return ""; // not a steps class, discard for the sake of steps registration
                    } catch (InstantiationException e) {
                        return ""; // not a steps class, discard for the sake of steps registration
                    }
            }

        };
        List<CandidateSteps> stepses = new GroovyStepsFactory(configuration, context).createCandidateSteps();
        stepses.add(new Steps(configuration, new PerScenarioWebDriverSteps(driverProvider)));
        stepses.add(new Steps(configuration, new WebDriverScreenshotOnFailure(driverProvider)));        
        return stepses;
    }

    protected Configuration configuration() {
        return makeConfiguration(this.getClass(), driverProvider);
    }

}
