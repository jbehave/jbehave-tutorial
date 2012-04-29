package org.jbehave.tutorials.etsy;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.failures.PendingStepStrategy;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.pico.PicoStepsFactory;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.FirefoxWebDriverProvider;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;
import org.jbehave.web.selenium.RemoteWebDriverProvider;
import org.jbehave.web.selenium.SauceContextOutput;
import org.jbehave.web.selenium.SauceLabsContextView;
import org.jbehave.web.selenium.SauceWebDriverProvider;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumContextOutput;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.picocontainer.behaviors.Storing;
import org.picocontainer.behaviors.ThreadCaching;
import org.picocontainer.classname.ClassLoadingPicoContainer;
import org.picocontainer.classname.ClassName;
import org.picocontainer.classname.DefaultClassLoadingPicoContainer;
import org.picocontainer.injectors.CompositeInjection;
import org.picocontainer.injectors.ConstructorInjection;
import org.picocontainer.injectors.SetterInjection;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

public class EtsyDotComStories extends JUnitStories {

    public EtsyDotComStories() {

        PendingStepStrategy pendingStepStrategy = new FailingUponPendingStep();
        CrossReference crossReference = new SauceContextOutput.SauceLabsCrossReference(new HashMap<String, String>())
                .withJsonOnly().withOutputAfterEachStory(true).withPendingStepStrategy(pendingStepStrategy)
                .excludingStoriesWithNoExecutedScenarios(true);

        SeleniumContext seleniumContext = new SeleniumContext();
        WebDriverProvider driverProvider;
        Format[] formats;
        ContextView contextView;

        if (System.getProperty("SAUCE_USERNAME") != null) {
            driverProvider = new SauceWebDriverProvider();
            formats = new Format[] { new SeleniumContextOutput(seleniumContext), CONSOLE, WEB_DRIVER_HTML };
            contextView = new SauceLabsContextView(driverProvider);
            crossReference.withThreadSafeDelegateFormat(new SauceContextOutput(driverProvider, seleniumContext,
                    new HashMap<String, String>()));
        } else if (System.getProperty("REMOTE") != null) {
            driverProvider = new RemoteWebDriverProvider();
            formats = new Format[] { CONSOLE, WEB_DRIVER_HTML };
            contextView = new ContextView.NULL();
        } else {
            driverProvider = new FirefoxWebDriverProvider();
            formats = new Format[] { new SeleniumContextOutput(seleniumContext), CONSOLE, WEB_DRIVER_HTML };
            contextView = new LocalFrameContextView().sized(640, 120);
        }

        StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
                .withCodeLocation(CodeLocations.codeLocationFromClass(EtsyDotComStories.class)).withFailureTrace(true)
                .withFailureTraceCompression(true).withDefaultFormats().withFormats(formats)
                .withCrossReference(crossReference);

        Configuration configuration = new SeleniumConfiguration().useWebDriverProvider(driverProvider)
                .useSeleniumContext(seleniumContext).useFailureStrategy(new RethrowingFailure())
                .usePendingStepStrategy(pendingStepStrategy)
                .useStoryControls(new StoryControls().doResetStateBeforeScenario(false))
                .useStepMonitor(new SeleniumStepMonitor(contextView, seleniumContext, crossReference.getStepMonitor()))
                .useStoryLoader(new LoadFromClasspath(EtsyDotComStories.class))
                .useStoryReporterBuilder(reporterBuilder);
        useConfiguration(configuration);

        final ThreadCaching primordialCaching = new ThreadCaching();
        MutablePicoContainer primordial = new PicoBuilder().withBehaviors(primordialCaching).build();
        primordial.addComponent(WebDriverProvider.class, driverProvider);

        // Groovy Steps - can be stateful per story.
        final Storing store = (Storing) new Storing().wrap(new CompositeInjection(new ConstructorInjection(),
                new SetterInjection("set", "setMetaClass")));
        ClassLoader currentClassLoader = this.getClass().getClassLoader();
        final DefaultClassLoadingPicoContainer pageObjects = new DefaultClassLoadingPicoContainer(currentClassLoader,
                store, primordial);
        pageObjects.change(Characteristics.USE_NAMES);
        // This loads all the Groovy page objects - can be stateful
        pageObjects.visit(new ClassName("pages.Home"), ".*\\.class", true,
                new DefaultClassLoadingPicoContainer.ClassVisitor() {
                    public void classFound(@SuppressWarnings("rawtypes") Class clazz) {
                        pageObjects.addComponent(clazz);
                    }
                });

        ClassLoadingPicoContainer steps = pageObjects.makeChildContainer("steps");
        steps.addComponent(new CacheFlusher(store));
        steps.addComponent(new ClassName("HousekeepingSteps"));
        steps.addComponent(new ClassName("EtsyDotComSteps"));
        // Before And After Steps registered by instance
        steps.addComponent(new PerStoryWebDriverSteps(driverProvider));
        steps.addComponent(new WebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()));
        // steps.addComponent(new WebDriverPageDumpOnFailure(driverProvider,
        // configuration.storyReporterBuilder()));
        steps.addComponent(new PerStoriesContextView(contextView));
        useStepsFactory(new PicoStepsFactory(configuration, steps));

    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(),
                asList("**/" + System.getProperty("storyFilter", "*") + ".story"),
                asList("**/etsy_given.story"));
    }

    public static class PerStoriesContextView {

        private final ContextView contextView;

        public PerStoriesContextView(ContextView contextView) {
            this.contextView = contextView;
        }

        @AfterStories
        public void afterStory() {
            contextView.close();
        }
    }

    public static class CacheFlusher {
        private final Storing store;

        public CacheFlusher(Storing store) {
            this.store = store;
        }

        @BeforeStory
        public void resetThreadLocalCaches() {
            store.resetCacheForThread();
        }
    }

}
