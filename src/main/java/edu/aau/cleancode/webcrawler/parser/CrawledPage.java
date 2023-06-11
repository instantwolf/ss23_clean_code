package edu.aau.cleancode.webcrawler.parser;

import edu.aau.cleancode.webcrawler.HttpResponseCategory;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CrawledPage<T> {

    HttpResponse<T> response;

    HtmlPage page;

    CrawledPage(HtmlPage page){
            this.page = page;
           // this.response = HttpResponseCategory..
    }

    CrawledPage(HttpResponse<T> response){
        this.response = response;
        this.page = new HtmlPage();
    }

}
