package org.jbehave.tutorials.etsy.steps;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.web.selenium.FirefoxWebDriverProvider;
import org.jbehave.web.selenium.PerStoriesWebDriverSteps;
import org.jbehave.web.selenium.WebDriverProvider;

public class JournaledStoriesSteps extends PerStoriesWebDriverSteps {

    private static final String JOURNAL_FIREFOX_COMMANDS = System.getProperty("JOURNAL_FIREFOX_COMMANDS", "false");

    public JournaledStoriesSteps(WebDriverProvider webDriverProvider) {
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
