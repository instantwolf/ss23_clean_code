package edu.aau.cleancode.webcrawler.parameter.crawldepth;

/**
 * This interface declares the methods that need to be implemented by a ParameterValidator class , to ensure that
 * the parameters provided to the webcrawler are correct
 */
public interface CrawlDepthParameterValidator {

    /**
     * @return an integer that contains the depth the crawler should use when visiting links from a certain start-page
     */
    boolean validateCrawlDepth();

    /**
     * @return a String holding the URL of the start-page
     */
    boolean validateStartURL();

    /**
     * @return a String holding either the ISO shortcut or the ISO XYZ name of the target language the titles shall be translated to
     */
    boolean validateTargetLanguage();



}
