package org.jbehave.tutorials.etsy.steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.web.selenium.FirefoxWebDriverProvider;
import org.jbehave.web.selenium.PerStoriesWebDriverSteps;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriverException;

public class LifecycleSteps extends PerStoriesWebDriverSteps {

    private static final String JOURNAL_FIREFOX_COMMANDS = System.getProperty("JOURNAL_FIREFOX_COMMANDS", "false");

    public LifecycleSteps(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    @Override
    @BeforeStories
    public void beforeStories() throws Exception {

        super.beforeStories();

        driverProvider.get().manage().deleteCookieNamed("uaid");
    }

    @Override
    @AfterStories
    public void afterStories() throws Exception {

        if (!JOURNAL_FIREFOX_COMMANDS.equals("false") && driverProvider instanceof FirefoxWebDriverProvider) {
            FirefoxWebDriverProvider.WebDriverJournal journal = ((FirefoxWebDriverProvider) driverProvider).getJournal();
            System.out.println("Journal of WebDriver Commands:");
            for (Object entry : journal) {
                System.out.println(entry);
            }
            ((FirefoxWebDriverProvider) driverProvider).clearJournal();
        }

        super.afterStories();
    }

}
