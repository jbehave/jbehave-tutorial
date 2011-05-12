package org.jbehave.tutorials.etsy;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.BatchFailures;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.core.steps.pico.PicoStepsFactory;
import org.jbehave.web.queue.WebQueue;
import org.jbehave.web.queue.WebQueueConfiguration;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.FirefoxWebDriverProvider;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;
import org.jbehave.web.selenium.RemoteWebDriverProvider;
import org.jbehave.web.selenium.SauceContextOutput;
import org.jbehave.web.selenium.SauceWebDriverProvider;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumContextOutput;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.picocontainer.Characteristics;
import org.picocontainer.ComponentFactory;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.picocontainer.behaviors.ThreadCaching;
import org.picocontainer.classname.ClassLoadingPicoContainer;
import org.picocontainer.classname.ClassName;
import org.picocontainer.classname.DefaultClassLoadingPicoContainer;
import org.picocontainer.injectors.CompositeInjection;
import org.picocontainer.injectors.ConstructorInjection;
import org.picocontainer.injectors.SetterInjection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

public class EtsyDotComStories extends JUnitStories {

    private WebDriverProvider driverProvider;
    private Configuration configuration;
    private ContextView contextView;
    private SeleniumContext seleniumContext = new SeleniumContext();
    private Format[] outputFormats;
    private String metaFilter;
    private final CrossReference crossReference = new CrossReference() {
        public String getMetaFilter() {
            return metaFilter;
        }
    }.withJsonOnly().withOutputAfterEachStory(true);

    public EtsyDotComStories() {

        if (System.getProperty("SAUCE_USERNAME") != null) {
            driverProvider = new SauceWebDriverProvider();
            outputFormats = new Format[] { CONSOLE, WEB_DRIVER_HTML };
            crossReference.withThreadSafeDelegateFormat(new SauceContextOutput(driverProvider));
            contextView = new ContextView.NULL();
        } else if (System.getProperty("REMOTE") != null) {
            driverProvider = new RemoteWebDriverProvider();
            outputFormats = new Format[] { CONSOLE, WEB_DRIVER_HTML };
            contextView = new ContextView.NULL();
        } else {
            outputFormats = new Format[] { new SeleniumContextOutput(seleniumContext), CONSOLE, WEB_DRIVER_HTML };
            driverProvider = new FirefoxWebDriverProvider();
            contextView = new LocalFrameContextView().sized(640, 120);
        }

        crossReference.excludingStoriesWithNoExecutedScenarios(true);

        StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder()
                .withCodeLocation(CodeLocations.codeLocationFromClass(EtsyDotComStories.class)).withFailureTrace(true)
                .withFailureTraceCompression(true).withDefaultFormats().withFormats(outputFormats)
                .withCrossReference(crossReference);

        addCrossReference(storyReporterBuilder, crossReference);

        configuration = new SeleniumConfiguration().useWebDriverProvider(driverProvider)
                .useSeleniumContext(seleniumContext).useFailureStrategy(new FailingUponPendingStep())
                .useStoryControls(new StoryControls().doResetStateBeforeScenario(false))
                .useStepMonitor(createStepMonitor())
                .useStoryLoader(new LoadFromClasspath(EtsyDotComStories.class.getClassLoader()))
                .useStoryReporterBuilder(storyReporterBuilder);

        useConfiguration(configuration);

        final MutablePicoContainer primordial = new PicoBuilder().withBehaviors(new ThreadCaching()).build();
        primordial.addComponent(WebDriverProvider.class, driverProvider);
        // multiThreaded.addComponent(...);

        // Groovy Steps - can be stateful per story.
        ComponentFactory cf = new ThreadCaching().wrap(new CompositeInjection(new ConstructorInjection(),
                new SetterInjection("set", "setMetaClass")));
        ClassLoader currentClassLoader = this.getClass().getClassLoader();
        final DefaultClassLoadingPicoContainer pageObjects = new DefaultClassLoadingPicoContainer(currentClassLoader, cf, primordial);
        pageObjects.change(Characteristics.USE_NAMES);
        // This loads all the Groovy page objects - can be stateful
        pageObjects.visit(new ClassName("pages.Home"), ".*\\.class", true,
                new DefaultClassLoadingPicoContainer.ClassVisitor() {
                    public void classFound(@SuppressWarnings("rawtypes") Class clazz) {
                        pageObjects.addComponent(clazz);
                    }
                });

        ClassLoadingPicoContainer steps = pageObjects.makeChildContainer("steps");
        steps.addComponent(new ClassName("housekeeping.EmptyCartIfNotAlready"));
        steps.addComponent(new ClassName("EtsyDotComSteps"));
        // Before And After Steps registered by instance
        steps.addComponent(new PerStoryWebDriverSteps(driverProvider));
        steps.addComponent(new WebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()));
        steps.addComponent(new PerStoriesContextView(contextView));

        useStepsFactory(new PicoStepsFactory(configuration, steps));

        // configuredEmbedder().embedderControls().doIgnoreFailureInStories(false);

    }

    protected StepMonitor createStepMonitor() {
        return new SeleniumStepMonitor(contextView, new SeleniumContext(), crossReference.getStepMonitor());
    }

    protected void addCrossReference(StoryReporterBuilder storyReporterBuilder, CrossReference crossReference) {
        storyReporterBuilder.withCrossReference(crossReference);
    }

    @Override
    public void run() {

        // only available post instantiation because of the way the jbehave
        // maven plugin
        // decorates an instance with configuration
        metaFilter = super.configuredEmbedder().metaFilters().toString();

        Embedder embedder = configuredEmbedder();
        if (System.getProperty("WEB_QUEUE") != null) {
            List<Future<Embedder.ThrowableStory>> futures = new ArrayList<Future<Embedder.ThrowableStory>>();
            BatchFailures batchFailures = new BatchFailures();
            String path = codeLocationFromClass(EtsyDotComStories.class).getPath();
            WebQueue queue = null;
            try {
                File navigatorDir = new File(new File(path).getParentFile().getParentFile(), "target/jbehave/view");
                WebQueueConfiguration webConfiguration = new WebQueueConfiguration();
                webConfiguration.useNavigatorDirectory(navigatorDir);
                String port = System.getProperty("jetty.port");
                if (port == null) {
                    webConfiguration.usePort(8090);
                } else {
                    webConfiguration.usePort(Integer.parseInt(port));
                }
                queue = new WebQueue(embedder, batchFailures, futures, webConfiguration);
                queue.start();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
            }
        } else {
            embedder.runStoriesAsPaths(storyPaths());
        }

    }

    @Override
    protected List<String> storyPaths() {
        if (System.getProperty("WEB_QUEUE") != null) {
            return new ArrayList<String>();
        }
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/"
                + storyFilter() + ".story"), null);
    }

    private String storyFilter() {
        String storyFilter = System.getProperty("storyFilter");
        if (storyFilter != null) {
            return storyFilter;
        } else {
            return "*";
        }
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

}
