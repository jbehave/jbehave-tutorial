package org.jbehave.tutorials.etsy.pages;

public interface AdvancedSearch {

    void go();

    void go(String section);

    void search(String thing);

    void subCategory(String subCategory);

    void searchFor(String thing);

}