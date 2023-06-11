package edu.aau.cleancode.webcrawler.parser;

import org.jsoup.Connection;

import java.util.Optional;

/**
 * This is an immutable class that holds the connection parameters that might be of interest for
 * the clean-code webcrawler project. Of course this class can be extended to provide additional parameters.
 *
 * The members of this class have a defaultValue set to our needs , if no value is provided
 */
public final class JsoupConnectionSettings {

    private final int timeout, maxBodySize;
    private final UserAgentString agent;
    private final boolean ignoreErrors, followRedirects;

    /** This constructor needs all parameters to be provided.
     *  This method does not check the provided values , nor assign standard values
     *  USE JsoupConnectionSettingsBuilder class instead!
     *
     * @param timeout
     * @param agent
     * @param ignoreErrors
     * @param followRedirects
     * @param maxBodySize
     */
    JsoupConnectionSettings(int timeout, UserAgentString agent, boolean ignoreErrors, boolean followRedirects, int maxBodySize){
        this.timeout = timeout;
        this.agent = agent;
        this.ignoreErrors = ignoreErrors;
        this.followRedirects = followRedirects;
        this.maxBodySize = maxBodySize;
    }


    /**
     * This method takes a (Jsoup) Connection and sets all of is parameters on the given object, then returns it
     */
    public void setConnectionParameters(Connection connection){
        connection.timeout(this.timeout);
        connection.userAgent(this.agent.toString());
        connection.ignoreHttpErrors(this.ignoreErrors);
        connection.followRedirects(this.followRedirects);
        connection.maxBodySize(this.maxBodySize);
    }
}
