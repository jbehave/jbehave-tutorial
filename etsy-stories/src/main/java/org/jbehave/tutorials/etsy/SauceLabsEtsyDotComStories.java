package org.jbehave.tutorials.etsy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.BatchFailures;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.web.queue.WebQueue;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.SauceContextOutput;
import org.jbehave.web.selenium.SauceWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;


public class SauceLabsEtsyDotComStories extends EtsyDotComStories {

    private SauceWebDriverProvider provider = new SauceWebDriverProvider();

    public SauceLabsEtsyDotComStories() {
    }

    @Override
    protected void decorateCrossReference(CrossReference crossReference) {
        crossReference.withThreadSafeDelegateFormat(new SauceContextOutput(provider));
    }

    @Override
    protected ContextView createContextView() {
        return new ContextView.NULL();
    }

    @Override
    protected Format[] createOutputFormats() {
        return new Format[] { WEB_DRIVER_HTML };
    }

    @Override
    protected WebDriverProvider createWebDriverProvider() {
        return provider;
    }


}
