package org.jbehave.tutorials.etsy;

import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.BatchFailures;
import org.jbehave.web.queue.WebQueue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

public class WebQueueEtsyStories extends SauceLabsEtsyDotComStories {

    public WebQueueEtsyStories() {
    }

    @Override
    public void run() {

        Embedder embedder = configuredEmbedder();
        List<Future<Embedder.ThrowableStory>> futures = new ArrayList<Future<Embedder.ThrowableStory>>();
        BatchFailures batchFailures = new BatchFailures();
        String path = codeLocationFromClass(SauceLabsEtsyDotComStories.class).getPath();
        WebQueue queue = null;
        try {
            File navigatorDir = new File(new File(path).getParentFile().getParentFile(), "src/main/storynavigator");
            queue = new WebQueue(embedder, batchFailures, futures, navigatorDir);
            queue.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
        }

    }

    @Override
    protected List<String> storyPaths() {
        return new ArrayList<String>();
    }

}
