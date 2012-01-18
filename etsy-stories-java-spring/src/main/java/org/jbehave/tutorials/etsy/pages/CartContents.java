package org.jbehave.tutorials.etsy.pages;

public interface CartContents {

    boolean hasItem(String item);

    void removeItem();

    int cartSize();
    
}