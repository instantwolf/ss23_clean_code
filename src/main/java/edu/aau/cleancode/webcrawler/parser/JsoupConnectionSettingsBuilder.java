package edu.aau.cleancode.webcrawler.parser;

import java.util.Optional;

public class JsoupConnectionSettingsBuilder {
    private int timeout = 10*1000;
    private UserAgentString agent = UserAgentString.FIREFOX_EXAMPLE;
    private boolean ignoreErrors = false;
    private boolean followRedirects = true;
    private int maxBodySize = 10;

    public JsoupConnectionSettingsBuilder setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public JsoupConnectionSettingsBuilder setAgent(UserAgentString agent) {
        this.agent = agent;
        return this;
    }

    public JsoupConnectionSettingsBuilder setIgnoreErrors(boolean ignoreErrors) {
        this.ignoreErrors = ignoreErrors;
        return this;
    }

    public JsoupConnectionSettingsBuilder setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
        return this;
    }

    public JsoupConnectionSettingsBuilder setMaxBodySize(int maxBodySize) {
        this.maxBodySize = maxBodySize;
        return this;
    }

    public JsoupConnectionSettings createJsoupConnectionSettings() {
        return new JsoupConnectionSettings(timeout, agent, ignoreErrors, followRedirects, maxBodySize);
    }
}