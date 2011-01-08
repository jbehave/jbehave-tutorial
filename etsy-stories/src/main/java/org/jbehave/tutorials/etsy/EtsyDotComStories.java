package org.jbehave.tutorials.etsy;

import groovy.lang.MetaClass;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.groovy.GroovyResourceFinder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.Steps;
import org.jbehave.core.steps.pico.PicoStepsFactory;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.PerStoriesWebDriverSteps;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumContextOutput;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.jbehave.web.selenium.TypeWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.picocontainer.Characteristics;
import org.picocontainer.ComponentAdapter;
import org.picocontainer.ComponentMonitor;
import org.picocontainer.LifecycleStrategy;
import org.picocontainer.Parameter;
import org.picocontainer.PicoCompositionException;
import org.picocontainer.classname.ClassLoadingPicoContainer;
import org.picocontainer.classname.ClassName;
import org.picocontainer.classname.DefaultClassLoadingPicoContainer;
import org.picocontainer.injectors.CompositeInjection;
import org.picocontainer.injectors.ConstructorInjection;
import org.picocontainer.injectors.SetterInjection;
import org.picocontainer.injectors.SetterInjector;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

public class EtsyDotComStories extends JUnitStories {

    private WebDriverProvider driverProvider = new TypeWebDriverProvider();
    private Configuration configuration;
    private static ContextView contextView = new LocalFrameContextView().sized(640, 120);
    private static SeleniumContext seleniumContext = new SeleniumContext();

    @Override
    public Configuration configuration() {
        configuration = makeConfiguration(this.getClass(), driverProvider);
        return configuration;
    }

    public static Configuration makeConfiguration(Class<?> embeddableClass, WebDriverProvider driverProvider) {

        return new SeleniumConfiguration()
                .useWebDriverProvider(driverProvider)
                .useSeleniumContext(seleniumContext)
                .useFailureStrategy(new FailingUponPendingStep())
                .useStepMonitor(new SeleniumStepMonitor(contextView, new SeleniumContext(), new SilentStepMonitor()))
                .useStoryLoader(new LoadFromClasspath(embeddableClass.getClassLoader()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                                .withDefaultFormats()
                                .withFormats(new SeleniumContextOutput(seleniumContext), CONSOLE, TXT, HTML, XML));
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        List<CandidateSteps> steps = makeGroovyCandidateSteps(configuration(), new GroovyResourceFinder(),
                driverProvider);
        steps.add(0, stepify(new PerStoriesWebDriverSteps(driverProvider))); // before
                                                                             // other
                                                                             // Groovy
                                                                             // steps
        steps.add(stepify(new WebDriverScreenshotOnFailure(driverProvider)));
        steps.add(stepify(new PerStoriesContextView()));
        return steps;
    }

    private Steps stepify(final Object object) {
        return new Steps(configuration, object);
    }

    @SuppressWarnings("serial")
    public static List<CandidateSteps> makeGroovyCandidateSteps(final Configuration configuration,
            GroovyResourceFinder resourceFinder, final WebDriverProvider webDriverProvider) {

        ClassLoadingPicoContainer comps = new DefaultClassLoadingPicoContainer(new CompositeInjection(
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
        comps.change(Characteristics.USE_NAMES);
        comps.addComponent(WebDriverProvider.class, webDriverProvider);
        comps.addComponent(new ClassName("pages.AdvancedSearch"));
        comps.addComponent(new ClassName("pages.CartContents"));
        comps.addComponent(new ClassName("pages.Home"));
        comps.addComponent(new ClassName("pages.SearchResults"));
        comps.addComponent(new ClassName("pages.Site"));
        comps.addComponent(new ClassName("pages.Buy"));
        comps.addComponent(new ClassName("pages.Treasury"));

        ClassLoadingPicoContainer steps = comps.makeChildContainer("steps");
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

        @AfterStories
        public void afterStory() {
            contextView.close();
        }
    }

}
