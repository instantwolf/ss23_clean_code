package edu.aau.cleancode.webcrawler.parameter;

/**
 * This interface declares the methods that need to be implemented from a ParameterProvider class , to ensure our webcrawler has
 * everything it needs to work.
 */
public interface ParameterProvider<T> {

    /**
     * @return an integer that contains the depth the crawler should use when visiting links from a certain start-page
     */
     T provideParameter();
}
