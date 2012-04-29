package org.jbehave.tutorials.etsy;

import java.util.Arrays;

public class EtsyStepdocs extends EtsyDotComStories {

    @Override
    public void run() throws Throwable {
        configuredEmbedder().reportStepdocsAsEmbeddables(Arrays.asList(EtsyDotComStories.class.getName()));
    }

}
