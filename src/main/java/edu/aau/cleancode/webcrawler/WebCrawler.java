package edu.aau.cleancode.webcrawler;

import edu.aau.cleancode.webcrawler.parser.HtmlHeading;
import edu.aau.cleancode.webcrawler.parser.HtmlParserAdapter;
import edu.aau.cleancode.webcrawler.translator.TranslateAPIRequestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * This class utilizes one Parser and one Translator to crawl Sites
 *
 * As an argument , it takes a root url, and crawls recursively until the given depth is reached
 */
public class WebCrawler implements Callable<List<CrawledPage>> {

    private int targetDepth = 0; // Maximum depth to crawl, Default doesn't recursively crawl

    private String startURL;
    private String targetLanguage;

    private List<CrawledPage> visitedPages; // Set of visited URLs, add

    private HtmlParserAdapter parserAdapter;

    public WebCrawler(String url,
                      String targetLanguage,
                      int targetDepth,
                      HtmlParserAdapter parserAdapter
                         ) {
        this.visitedPages = new ArrayList();
        this.startURL = url.toLowerCase();
        this.targetLanguage = targetLanguage;
        this.targetDepth = targetDepth;
        this.parserAdapter = parserAdapter;
    }

    public List<CrawledPage> crawl(){
        return crawl(this.startURL,0,null);
    }


    private List<CrawledPage> crawl(String url, int depth, CrawledPage parentPage) {
        if (depth > this.targetDepth || visitedPages.stream().filter((x -> x.url.equals(url))).count() > 0) {
            return new ArrayList<CrawledPage>();
        }

        try {
            CrawledPage page = new CrawledPage(url, parentPage, depth);
            visitedPages.add(page);

            List<HtmlHeading> headings = retrieveHeadingsFromParser(page);
            page.setHeadings(headings);

            List<String> links = retrieveLinksFromParser(page);
            page.setLinks(links);

            if (depth < this.targetDepth) {
                for (String link : page.getLinks()) {
                    crawl(link, depth + 1, page);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }{
            return this.visitedPages;
        }
    }

    private List<HtmlHeading> retrieveHeadingsFromParser(CrawledPage page) throws IOException {
        List<HtmlHeading> originalHeadings =  parserAdapter.getHeadings(page.url);
        List<HtmlHeading> translatedHeadings = TranslateAPIRequestHandler.translateHeadings(originalHeadings, this.targetLanguage);
        return translatedHeadings;
    }

    private List<String> retrieveLinksFromParser(CrawledPage page) throws IOException {
        List<String> links =  parserAdapter.getLinks(page.url);
        return links;
    }


    public List<CrawledPage> getResults(){
        return this.visitedPages;
    }

    @Override
    public List<CrawledPage> call() throws Exception {
        return crawl();
    }
}







