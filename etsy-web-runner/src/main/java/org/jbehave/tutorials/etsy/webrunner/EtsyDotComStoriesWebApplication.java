package org.jbehave.tutorials.etsy.webrunner;


import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.tutorials.etsy.EtsyDotComStories;
import org.jbehave.web.runner.wicket.WebRunnerApplication;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;

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
    };

    /**
     * Overridden as we do not want the context view dialog to come up.
     * @return
     */
    protected List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration,
                  new PerStoryWebDriverSteps(mainEtsyStories.getDriverProvider()))
               .createCandidateSteps();
    }

    protected Configuration configuration() {
        configuration = mainEtsyStories.configuration();
        return configuration;
    }

}
