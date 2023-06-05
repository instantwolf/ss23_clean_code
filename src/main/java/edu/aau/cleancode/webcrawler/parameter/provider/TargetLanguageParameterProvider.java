package edu.aau.cleancode.webcrawler.parameter.provider;

/**
 * This interface declares the methods that need to be implemented from a ParameterProvider class , to ensure our webcrawler has
 * everything it needs to work.
 */
public interface TargetLanguageParameterProvider {

    /**
     * @return a String holding either the ISO shortcut or the ISO XYZ name of the target language the titles shall be translated to
     */
    String getTargetLanguage();



}
