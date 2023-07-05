package edu.aau.cleancode.webcrawler;

import edu.aau.cleancode.webcrawler.parser.HtmlHeading;
import edu.aau.cleancode.webcrawler.parser.HtmlPage;

import java.util.ArrayList;
import java.util.List;

public class CrawledPage {

    List<HtmlHeading> headings;
    List<String> links;

    String url;

    int depth;

    CrawledPage parent;


    CrawledPage(String url, CrawledPage parent, int pageDepth){
        headings = new ArrayList<>();
        links = new ArrayList<>();
        this.url = url;
        this.parent = parent;
        this.depth = pageDepth;
    }

    public void addHeading(HtmlHeading heading){
        this.headings.add(heading);
    }

    public void setHeadings(List<HtmlHeading> headings){
        this.headings = headings;
    }

    public List<String> getLinks(){
        return this.links;
    }


    public List<HtmlHeading> getHeadings(){
        return this.headings;
    }


    public void addLink(String url){
        this.links.add(url);
    }

    public void setLinks(List<String> links){
        this.links = links;
    }

}
