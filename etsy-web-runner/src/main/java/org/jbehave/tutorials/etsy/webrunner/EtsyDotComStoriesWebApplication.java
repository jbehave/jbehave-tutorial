package org.jbehave.tutorials.etsy.webrunner;


import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.tutorials.etsy.EtsyDotComStories;
import org.jbehave.web.runner.wicket.WebRunnerApplication;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;

import java.util.Collection;
import java.util.List;

public class EtsyDotComStoriesWebApplication extends WebRunnerApplication {

    private Configuration configuration;
    private EtsyDotComStories mainEtsyStories = new EtsyDotComStories() {

        /**
         * Overridden as we do not want the context view dialog to come up.
         * @return
         */
        @Override
        protected void setContextView(ContextView ignore) {
            super.setContextView(new ContextView.NULL());
        }

        @Override
        protected void addCrossReference(StoryReporterBuilder storyReporterBuilder, CrossReference crossReference) {
            // let's not for the web-runner.
        }

        @Override
        protected StepMonitor createStepMonitor() {
            return new SilentStepMonitor();
        }

        /**
         * Overridden as we do not want the context view dialog to come up.
         * @return
         */
        @Override
        protected Collection<? extends CandidateSteps> beforeAndAfterSteps() {
            return new InstanceStepsFactory(configuration,
                      new PerStoryWebDriverSteps(mainEtsyStories.getDriverProvider()))
                   .createCandidateSteps();
        }
    };

    protected List<CandidateSteps> candidateSteps() {
        return mainEtsyStories.candidateSteps();
    }


    protected Configuration configuration() {
        configuration = mainEtsyStories.configuration();
        return configuration;
    }

}
