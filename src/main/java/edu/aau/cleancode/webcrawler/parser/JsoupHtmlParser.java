package edu.aau.cleancode.webcrawler.parser;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;


public class JsoupHtmlParser {

    Connection connection;

    /**
     * Creates a connection that runs on jsoup default settings.
     * (Timeout etc.)
     */
    JsoupHtmlParser(){
        initSessionAndSetParameters(new JsoupConnectionSettingsBuilder());
    }

    JsoupHtmlParser(JsoupConnectionSettingsBuilder builder){
        initSessionAndSetParameters(builder);
    }


    public Elements getElements(URL Website, List<ParseSelector> selectors) throws IOException, MalformedURLException, HttpStatusException, UnsupportedMimeTypeException, SocketTimeoutException{
         return this.getElements(Website, selectors, Connection.Method.GET);
    }

    public Elements getElements(URL Website, List<ParseSelector> selectors, Connection.Method method) throws IOException, MalformedURLException, HttpStatusException, UnsupportedMimeTypeException, SocketTimeoutException {

         setRequestParameters(Website,method);

         //fetch site html
         Document document =  Jsoup.connect(Website.toString()).get();
         //restrict to link selectors
        //List<JsoupSelector> linkselectors =
        // selectors.stream().filter(x -> x.getSelectorType() == JsoupSelectorType.HEADINGS).collect(Collectors.toList());


        Elements result = new Elements().empty();

        for (ParseSelector selector : selectors) {
           result.addAll(queryDocumentAccordingToSelector(document, selector));
        }

        return result;
    }


    private static Elements queryDocumentAccordingToSelector(Document document, ParseSelector selector){
        return document.select(selector.getSelector());
    }



    /**
     * sets the required parameters before a valid request can be submitted
     */
    private void setRequestParameters(URL Website, Connection.Method method){
        this.connection.method(method);
        this.connection.url(Website);
    }

    private void initSession(){
        this.connection = Jsoup.newSession();
    }

    private void initSessionAndSetParameters(JsoupConnectionSettingsBuilder builder){
        initSession();
        builder.createJsoupConnectionSettings().setConnectionParameters(this.connection);
    }
}
