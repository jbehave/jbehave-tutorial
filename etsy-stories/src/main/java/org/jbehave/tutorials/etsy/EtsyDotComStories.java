package org.jbehave.tutorials.etsy;

import groovy.lang.MetaClass;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.core.steps.pico.PicoStepsFactory;
import org.jbehave.web.selenium.*;
import org.picocontainer.*;
import org.picocontainer.classname.ClassLoadingPicoContainer;
import org.picocontainer.classname.ClassName;
import org.picocontainer.classname.DefaultClassLoadingPicoContainer;
import org.picocontainer.injectors.CompositeInjection;
import org.picocontainer.injectors.ConstructorInjection;
import org.picocontainer.injectors.SetterInjection;
import org.picocontainer.injectors.SetterInjector;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

public class EtsyDotComStories extends JUnitStories {

    private StepMonitor stepMonitor = new SilentStepMonitor();
    private WebDriverProvider driverProvider = new TypeWebDriverProvider();
    private Configuration configuration;
    private ContextView contextView = new LocalFrameContextView().sized(640, 120);
    private SeleniumContext seleniumContext = new SeleniumContext();
    private boolean shouldDoDryRun = false;
    private org.jbehave.core.reporters.Format[] outputFormats = new org.jbehave.core.reporters.Format[]
            {new SeleniumContextOutput(seleniumContext), CONSOLE, WEB_DRIVER_HTML} ;
    private XRefCapturingFormatAndStepMonitor xRefCapturingFormatAndStepMonitor = new XRefCapturingFormatAndStepMonitor();

    public EtsyDotComStories() {
        if (System.getProperty("jb-xref") != null) {
            shouldDoDryRun = true;
            outputFormats = new org.jbehave.core.reporters.Format[] {xRefCapturingFormatAndStepMonitor};
            stepMonitor = xRefCapturingFormatAndStepMonitor;
        }
    }

    @Override
    public Configuration configuration() {
        configuration = seleniumConfiguration(this.getClass(), driverProvider);
        return configuration;
    }

    private Configuration seleniumConfiguration(Class<?> embeddableClass, WebDriverProvider driverProvider) {
        return new SeleniumConfiguration()
                .useWebDriverProvider(driverProvider)
                .useSeleniumContext(seleniumContext)
                .doDryRun(shouldDoDryRun)
                .useFailureStrategy(new FailingUponPendingStep())
                .useStepMonitor(new SeleniumStepMonitor(contextView, new SeleniumContext(), stepMonitor))
                .useStoryLoader(new LoadFromClasspath(embeddableClass.getClassLoader()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                                .withFailureTrace(true)
                                .withDefaultFormats()
                                .withFormats(outputFormats));
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        List<CandidateSteps> steps = new ArrayList<CandidateSteps>();
        steps.addAll(beforeAndAfterSteps());
        steps.addAll(groovySteps());
        return steps;
    }

    private Collection<? extends CandidateSteps> beforeAndAfterSteps() {
        return new InstanceStepsFactory(configuration, new PerStoriesWebDriverSteps(driverProvider),
                new PerStoriesContextView(contextView), new WebDriverScreenshotOnFailure(driverProvider))
                .createCandidateSteps();
    }

    @SuppressWarnings("serial")
    public List<CandidateSteps> groovySteps() {
        ClassLoadingPicoContainer container = new DefaultClassLoadingPicoContainer(new CompositeInjection(
                new ConstructorInjection(), new SetterInjection() {
                    @Override
                    public <T> ComponentAdapter<T> createComponentAdapter(ComponentMonitor monitor,
                            LifecycleStrategy lifecycleStrategy, Properties componentProperties, Object componentKey,
                            Class<T> componentImplementation, Parameter... parameters) throws PicoCompositionException {
                        return new SetterInjector<T>(componentKey, componentImplementation, parameters, monitor, "set",
                                true) {
                            @Override
                            protected boolean isInjectorMethod(Method method) {
                                // this could go into PicoContainer itself
                                boolean b = method.getParameterTypes()[0] != MetaClass.class;
                                return b && super.isInjectorMethod(method);
                            }
                        };
                    }
                }));
        container.change(Characteristics.USE_NAMES);
        container.addComponent(WebDriverProvider.class, driverProvider);
        container.addComponent(new ClassName("pages.AdvancedSearch"));
        container.addComponent(new ClassName("pages.CartContents"));
        container.addComponent(new ClassName("pages.Home"));
        container.addComponent(new ClassName("pages.SearchResults"));
        container.addComponent(new ClassName("pages.Site"));
        container.addComponent(new ClassName("pages.Buy"));
        container.addComponent(new ClassName("pages.Treasury"));

        ClassLoadingPicoContainer steps = container.makeChildContainer("steps");
        steps.addComponent(new ClassName("housekeeping.EmptyCartIfNotAlready"));
        steps.addComponent(new ClassName("EtsyDotComSteps"));

        return new PicoStepsFactory(configuration, steps).createCandidateSteps();
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder()
                .findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/*.story"), null);
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

    @Override
    public void run() throws Throwable {
        try {
            super.run();
        } finally {
            xRefCapturingFormatAndStepMonitor.outputToFiles();
        }
    }

}
