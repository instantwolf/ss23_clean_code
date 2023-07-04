package edu.aau.cleancode.webcrawler.parser;

import org.jsoup.HttpStatusException;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The purpose of this class is , to make the rest of the software independent of the Jsoup specific objects (Elements)
 * and add abstraction (is supposed work as a gateway for this module)
 */
public class JsoupHtmlParserAdapter implements HtmlParserAdapter {

    JsoupHtmlParser parser;

    private List<ParseSelector> selectors;

    private static final ParseSelector defaultLinkSelector = new ParseSelector("a[href]", ParseSelectorType.LINKS);
    private static final ParseSelector defaultHeadingSelector = new ParseSelector("h1,h2,h3,h4,h5,h6", ParseSelectorType.HEADINGS);


     public JsoupHtmlParserAdapter(){
        this.parser = new JsoupHtmlParser();
        this.selectors = JsoupHtmlParserAdapter.initDefaultSelectors();
    }


    public HtmlPage crawlPage(URL website)throws IOException, MalformedURLException, HttpStatusException, UnsupportedMimeTypeException, SocketTimeoutException {
         HtmlPage crawledpage = new HtmlPage();
         try{
             crawledpage.headings = getHeadings(website.toString());
             crawledpage.links = getLinks(website.toString());
         }
         catch (Exception e){
            e.printStackTrace();
            //TODO: handle exceptions here
         }

         return crawledpage;
    }

    @Override
    public List<HtmlHeading> getHeadings(String target) throws IOException, MalformedURLException {

         URL url = new URL(target);

        List<Element> elements =  parser.getElements(url,
                this.selectors.stream()
                        .filter(x -> x.type.equals(ParseSelectorType.HEADINGS))
                        .collect(Collectors.toList())
        );

        return elements.stream().map(this::convertElementToHeading).collect(Collectors.toList());
     }

    private HtmlHeading convertElementToHeading(Element e){
        return new HtmlHeading(e.tag().toString(), e.wholeText().toString());
    }


    @Override
    public List<String> getLinks(String target) throws IOException {
        URL url = new URL(target);

        List<Element> elements =  parser.getElements(url,
                this.selectors.stream()
                        .filter(x -> x.type.equals(ParseSelectorType.LINKS))
                        .collect(Collectors.toList()));

        return elements.stream().map(this::elementToAbsoluteURL).collect(Collectors.toList());
     }


     private String elementToAbsoluteURL(Element elem){
               return elem.absUrl("href");
     }





    private static List<ParseSelector> initDefaultSelectors(){
         List<ParseSelector> selectors = new ArrayList<>();
         selectors.add(JsoupHtmlParserAdapter.defaultLinkSelector);
         selectors.add(JsoupHtmlParserAdapter.defaultHeadingSelector);
         return selectors;
    }





}
