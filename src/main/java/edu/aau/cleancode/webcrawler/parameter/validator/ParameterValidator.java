package edu.aau.cleancode.webcrawler.parameter.validator;

/**
 * This interface declares the methods that need to be implemented by a ParameterValidator class , to ensure that
 * the parameters provided to the webcrawler are correct
 */
public interface ParameterValidator {

    /**
     * @return a boolean that indicates whether the provided target language fits the validation criteria
     */
    boolean validateCrawlDepth();

    /**
     * @return a boolean that indicates whether the provided start-page url fits the validation criteria
     */
    boolean validateStartUrl();

    /**
     * @return a boolean that indicates whether the provided target language fits the validation criteria
     */
    boolean validateTargetLanguage();
}
