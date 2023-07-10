package edu.aau.cleancode.webcrawler.markdown;

import edu.aau.cleancode.webcrawler.CrawledPage;
import edu.aau.cleancode.webcrawler.parser.HtmlHeading;
import edu.aau.cleancode.webcrawler.parser.HtmlHeadingLevel;

import java.util.List;

public class MarkDownStringGenerator {

    private String markDownString;


    public MarkDownStringGenerator(){
        markDownString = "";
    }

    public String generateMarkDown(List<String> inputUrls,int targetDepth, String sourceLanguage, String targetLanguage,
    List<CrawledPage> crawledPages){
        addRow("input:");
        printInputUrls(inputUrls);
        addRow("depth: "+targetDepth);
        addRow("source language: "+sourceLanguage);
        addRow("target language: "+targetLanguage);
        addRow("summary: ");
        printCrawlResultSet(crawledPages);

        return this.markDownString;
    }

    private void printCrawlResultSet(List<CrawledPage> crawledPages) {
        for (CrawledPage page: crawledPages) {
                printCrawledPage(page,0);
        }
    }

    private void printCrawledPage(CrawledPage crawledPage, int depth){
        String rowPrefix = getDepthDependendRowPrefix(depth);
        printPageHeadings(crawledPage.getHeadings(), rowPrefix);
        printPageLinks(crawledPage.getLinks(),rowPrefix);
        addRow("\n"); //after link section another newline
    }


    private String getDepthDependendRowPrefix(int depth){
        String prefix = "-".repeat(depth);
        return (prefix.length() == 0 ? prefix : prefix+">");
    }
    private void printPageHeadings(List<HtmlHeading> headings, String rowPrefix){
        for (HtmlHeading heading: headings) {
            printPageHeading(heading, rowPrefix);
        }
    }

    private void printPageHeading(HtmlHeading heading, String rowPrefix){
        addRow(getHeadingPrefix(heading) + " "+rowPrefix+ " "+ heading.getContent());
    }

    private String getHeadingPrefix(HtmlHeading heading){
        return "#".repeat(heading.getHeadingLevelInt());
    }
    private void printInputUrls(List<String> inputUrls){
        for (String url: inputUrls) {
            addRow(" \t *"+linkToMarkDown(url));
        }
    }

    private String linkToMarkDown(String link){
        return" <a>" + link +"</a>";
    }

    //prints newline before a link, and <br> tag within the same row
    private void printPageLinks(List<String> links, String rowPrefix){
        for (String link: links) {
            printLink(link, rowPrefix);
        }
    }

    private void printLink(String link, String rowPrefix){
        this.markDownString += "\n"; //pre-link newline
        addRow(rowPrefix+ " "+linkToMarkDown(link));
    }

    private  void addRow(String rowToAdd){
        this.markDownString += "<br>"+rowToAdd+"\n";
    }
}
