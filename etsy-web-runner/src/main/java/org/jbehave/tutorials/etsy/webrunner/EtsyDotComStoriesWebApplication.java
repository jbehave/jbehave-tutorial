package org.jbehave.tutorials.etsy.webrunner;


import java.util.List;

import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.tutorials.etsy.EtsyDotComStories;
import org.jbehave.web.runner.wicket.WebRunnerApplication;

public class EtsyDotComStoriesWebApplication extends WebRunnerApplication {

    protected List<CandidateSteps> candidateSteps() {
        return new EtsyDotComStories().candidateSteps();
    }

}
