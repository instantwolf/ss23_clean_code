package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
public class WebCrawler {

    private int depth = 0; // Maximum depth to crawl

    private String url;
    private String targetLanguage;

    private HashSet<String> visitedUrls; // Set of visited URLs

    private TranslateAPIRequestHandler translateAPIRequestHandler;

    public WebCrawler(String url, String targetLanguage, int depth) {
        this.visitedUrls = new HashSet<>();
        this.url = url;
        this.targetLanguage = targetLanguage;
        this.depth = depth;
        this.translateAPIRequestHandler = new TranslateAPIRequestHandler();
    }

    public void crawl(String url, int depth) {
        if (depth > this.depth || visitedUrls.contains(url)) {
            return;
        }

        visitedUrls.add(url);

        try {
            Document document = Jsoup.connect(url).get();

            String heading = document.select("head title").text();

            System.out.println("Heading: " + heading);
            TranslateAPIRequestHandler.translateRequest(heading, targetLanguage);
            Elements links = document.select("a[href]");

            for (Element link : links) {

                String href = link.absUrl("href");
                crawl(href, depth + 1);
            }
            System.out.println("URL: " + url);
            System.out.println("Depth: " + depth);
        } catch (Exception e2) {
            System.err.println("Could not crawl " + url + ": " + e2.getMessage());
            System.out.println("URL: " + url + " <broken link> ");
            System.out.println("Depth: " + depth);
        }
    }
    public HashSet<String> getLinks()
    {
        return visitedUrls;
    }

}
