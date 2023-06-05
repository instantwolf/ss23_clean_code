package edu.aau.cleancode.webcrawler.parameter.crawldepth;

/**
 * This interface declares the methods that need to be implemented from a ParameterProvider class , to ensure our webcrawler has
 * everything it needs to work.
 */
public interface CrawlDepthParameterProvider {

    /**
     * @return an integer that contains the depth the crawler should use when visiting links from a certain start-page
     */
    int getCrawlDepth();
}
