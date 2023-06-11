package edu.aau.cleancode.webcrawler.parser;

/**
 * Latest User-Agent example strings for representative browsers according to "https://www.useragentstring.com/"
 */
public enum UserAgentString {

    FIREFOX_EXAMPLE("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:101.0) Gecko/20100101 Firefox/101.0"),
    CHROME_EXAMPLE("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36"),

    SAFARI_EXAMPLE("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A"),
    EDGE_EXAMPLE("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.19582");


    private final String userAgentString;

     UserAgentString(String userAgentString){
        this.userAgentString = userAgentString;
    }

    public String getUserAgentString(){
       return this.userAgentString;
    }


}
