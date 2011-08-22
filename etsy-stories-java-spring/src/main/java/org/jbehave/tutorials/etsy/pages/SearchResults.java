package org.jbehave.tutorials.etsy.pages;

import org.seleniumhq.selenium.fluent.OngoingMultipleElements;

public interface SearchResults {

    String buyFirst(String thing);

    OngoingMultipleElements getResultElements();

}