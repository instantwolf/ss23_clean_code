package edu.aau.cleancode.webcrawler.parser;


import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * This is an interface for all adapters , that shall provide the same functionality independent of
 * the underlying implementation of the content fetching / parsing per se.
 * For our use, it is sufficient, if this interface requires getHeadings and getLinks Methods that each return
 * the corresponding business object.
 *
 */
public interface HtmlParserAdapter {

    /**
     * This method takes an URL and returns all Headings found within the given HTML
     * of the provided URL
     * @param URL: contains the reference to the paged to be parsed
     * @return returns a list of the found Headings within the page html
     */
    List<HtmlHeading> getHeadings(String URL) throws IOException;

    /**
     * This method takes an URL
     * @return
     */
    List<String> getLinks(String URL) throws IOException;
}
