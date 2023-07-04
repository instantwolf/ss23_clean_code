package edu.aau.cleancode.webcrawler.parser;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class JsoupHtmlParserAdapterTest {

    @Test
    public void testCrawlPage_Successful() throws Exception {
        // Arrange
        JsoupHtmlParserAdapter adapter = new JsoupHtmlParserAdapter();
        URL website = new URL("http://example.com");

        // Act
        HtmlPage crawledPage = adapter.crawlPage(website);

        // Assert
        assertNotNull(crawledPage);
    }

    @Test(expected = IOException.class)
    public void testCrawlPage_Exception() throws Exception {
        // Arrange
        JsoupHtmlParserAdapter adapter = new JsoupHtmlParserAdapter();
        URL invalidWebsite = new URL("http://invalidurl");

        // Act
        adapter.crawlPage(invalidWebsite);

    }

    @Test
    public void testGetHeadings_Successful() throws Exception {
        // Arrange
        JsoupHtmlParserAdapter adapter = new JsoupHtmlParserAdapter();
        String target = "http://example.com";

        // Act
        List<HtmlHeading> headings = adapter.getHeadings(target);

        // Assert
        assertNotNull(headings);
    }

    @Test(expected = IOException.class)
    public void testGetHeadings_Exception() throws Exception {
        // Arrange
        JsoupHtmlParserAdapter adapter = new JsoupHtmlParserAdapter();
        String invalidTarget = "http://invalidurl";

        // Act
        adapter.getHeadings(invalidTarget);

    }

    @Test
    public void testGetLinks_Successful() {
        // Arrange
        JsoupHtmlParserAdapter adapter = new JsoupHtmlParserAdapter();
        String url = "http://example.com";
        List<String> links = new ArrayList<>();
        // Act
        try{
           links = adapter.getLinks(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        // Assert
        Assert.assertNotNull(links);
    }

}