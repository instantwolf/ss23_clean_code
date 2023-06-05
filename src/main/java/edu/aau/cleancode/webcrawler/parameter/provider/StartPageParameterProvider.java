package edu.aau.cleancode.webcrawler.parameter.provider;

/**
 * This interface declares the methods that need to be implemented from a ParameterProvider class , to ensure our webcrawler has
 * everything it needs to work.
 */
public interface StartPageParameterProvider {
    /**
     * @return a String holding the URL of the start-page
     */
    String getStartURL();
}
