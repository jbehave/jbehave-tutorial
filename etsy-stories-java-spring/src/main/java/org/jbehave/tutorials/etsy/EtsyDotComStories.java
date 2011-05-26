package org.jbehave.tutorials.etsy;

import java.util.List;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumContextOutput;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.springframework.context.ApplicationContext;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

public class EtsyDotComStories extends JUnitStories {

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
        outputFormats = new Format[] { new SeleniumContextOutput(seleniumContext), CONSOLE, WEB_DRIVER_HTML };
        contextView = new LocalFrameContextView().sized(640, 120);
        crossReference.excludingStoriesWithNoExecutedScenarios(true);

        StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder()
                .withCodeLocation(CodeLocations.codeLocationFromClass(EtsyDotComStories.class)).withFailureTrace(true)
                .withFailureTraceCompression(true).withDefaultFormats().withFormats(outputFormats)
                .withCrossReference(crossReference);

        addCrossReference(storyReporterBuilder, crossReference);

        configuration = new SeleniumConfiguration()
                .useSeleniumContext(seleniumContext).useFailureStrategy(new FailingUponPendingStep())
                .useStoryControls(new StoryControls().doResetStateBeforeScenario(false))
                .useStepMonitor(createStepMonitor())
                .useStoryLoader(new LoadFromClasspath(EtsyDotComStories.class.getClassLoader()))
                .useStoryReporterBuilder(storyReporterBuilder);

        useConfiguration(configuration);

        ApplicationContext context = new SpringApplicationContextFactory("etsy-steps.xml").createApplicationContext();
        useStepsFactory(new SpringStepsFactory(configuration, context));

        // configuredEmbedder().embedderControls().doIgnoreFailureInStories(false);

    }

    protected StepMonitor createStepMonitor() {
        return new SeleniumStepMonitor(contextView, new SeleniumContext(), crossReference.getStepMonitor());
    }

    protected void addCrossReference(StoryReporterBuilder storyReporterBuilder, CrossReference crossReference) {
        storyReporterBuilder.withCrossReference(crossReference);
    }

    @Override
    protected List<String> storyPaths() {
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
