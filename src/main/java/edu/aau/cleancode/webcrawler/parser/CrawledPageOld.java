package edu.aau.cleancode.webcrawler.parser;

import java.net.http.HttpResponse;

public class CrawledPageOld<T> {

    HttpResponse<T> response;

    HtmlPage page;

    CrawledPageOld(HtmlPage page){
            this.page = page;
           // this.response = HttpResponseCategory..
    }

    CrawledPageOld(HttpResponse<T> response){
        this.response = response;
        this.page = new HtmlPage();
    }

}
