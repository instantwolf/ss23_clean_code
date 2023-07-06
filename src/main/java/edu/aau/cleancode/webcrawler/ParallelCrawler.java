package edu.aau.cleancode.webcrawler;

import edu.aau.cleancode.webcrawler.parser.HtmlParserAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelCrawler {

    private List<String> urls;

    private List<WebCrawler> crawlers;

    private List<CrawledPage> resultSet;

    public ParallelCrawler(List<String> urls, String targetLanguage, int depth, HtmlParserAdapter adapter) {
        resultSet = new ArrayList<>();
        this.crawlers = new ArrayList<>();
        this.urls = urls;
        for (String url: urls) { //init crawlers based on urls
           crawlers.add(new WebCrawler(url, targetLanguage,depth,adapter));
        }

    }

    public List<CrawledPage> crawl()throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(crawlers.size());
        List<Future<List<CrawledPage>>> result = executorService.invokeAll(crawlers);

        for (Future<List<CrawledPage>> futureCrawlResult: result) {
            List<CrawledPage> crawlResult = futureCrawlResult.get();
            mergeResultSets(crawlResult);
        }
        return this.resultSet;
    }

    private void mergeResultSets(List<CrawledPage> crawlResult){
        //if page is already contained, then ignore the result
        if(this.resultSet.contains(crawlResult)){
            return;
        }
        this.resultSet.addAll(crawlResult);
    }
}
