package org.jbehave.tutorials.etsy.webrunner;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.tutorials.etsy.EtsyDotComStories;
import org.jbehave.web.runner.wicket.WebRunnerApplication;

public class EtsyDotComStoriesWebApplication extends WebRunnerApplication {

    private EtsyDotComStories stories = new EtsyDotComStories();

    protected List<CandidateSteps> candidateSteps() {
        return stories.stepsFactory().createCandidateSteps();
    }

    protected Configuration configuration() {
        return stories.configuration();
    }

}
