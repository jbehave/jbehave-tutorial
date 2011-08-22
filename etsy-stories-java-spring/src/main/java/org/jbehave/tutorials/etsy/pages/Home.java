package org.jbehave.tutorials.etsy.pages;


public interface Home {

    void go(); 
    
    void go(String section);
    
    void search(String thing);
    
    void goToBuySection();
    
}
