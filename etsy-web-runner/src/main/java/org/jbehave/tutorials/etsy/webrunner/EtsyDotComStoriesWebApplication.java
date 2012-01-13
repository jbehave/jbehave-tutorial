package org.jbehave.tutorials.etsy.webrunner;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.tutorials.etsy.EtsyDotComStories;
import org.jbehave.web.runner.wicket.WebRunnerApplication;

public class EtsyDotComStoriesWebApplication extends WebRunnerApplication {

    private EtsyDotComStories stories = new EtsyDotComStories();

    protected InjectableStepsFactory stepsFactory() {
        return stories.stepsFactory();
    }

    protected Configuration configuration() {
        return stories.configuration();
    }

}
