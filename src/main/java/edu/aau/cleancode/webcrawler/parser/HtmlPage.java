package edu.aau.cleancode.webcrawler.parser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlPage {

    List<HtmlHeading> headings;
    List<String> links;

    HtmlPage(){
        headings = new ArrayList<>();
        links = new ArrayList<>();
    }

}
