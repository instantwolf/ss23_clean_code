package edu.aau.cleancode.webcrawler.parameter.provider;

import edu.aau.cleancode.webcrawler.parameter.provider.ParameterProvider;

/**
 * This class is the default implementation of our ParameterProviderInterface and provides default values for each
 * parameter that is essential for the webcrawler
 */
public class DefaultParameterProvider implements ParameterProvider {


    @Override
    public int getCrawlDepth() {
        return 2;
    }

    @Override
    public String getStartURL() {
        return "www.wikipedia.org";
    }

    @Override
    public String getTargetLanguage() {
        return "de";
    }
}
