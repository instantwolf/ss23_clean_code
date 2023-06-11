package edu.aau.cleancode.webcrawler;

import edu.aau.cleancode.webcrawler.parser.HtmlHeading;
import edu.aau.cleancode.webcrawler.parser.HtmlParserAdapter;
import edu.aau.cleancode.webcrawler.parser.JsoupHtmlParserAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    /**
    public static void main(String[] args) throws IOException {
        // Press ⌥⏎ with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome! ");


        System.out.println("Enter your URL like: http://www.example.com");
        int depth = 0;
        String url = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            url = reader.readLine();

        if(ValidateURL.isValidURL(url)) {
            System.out.println("Enter your targetL");
            String targetLanguage = reader.readLine();
            System.out.println("Enter your depth");
            try {
                depth = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println("Enter a valid number");
            }
            System.out.println(url + " " + " " + targetLanguage + " " + depth);
            WebCrawler crawler = new WebCrawler(url, targetLanguage, depth);
            crawler.crawl(url, 0);
        }
        else
        {
            System.out.println("Wrong URL type");
        }
    }
     */

    public static void main(String[] args) {
        try{
            JsoupHtmlParserAdapter adapter = new JsoupHtmlParserAdapter();

            List<HtmlHeading> headings = adapter.getHeadings("https://en.wikipedia.org/wiki/Web_crawler");

            System.out.println(headings.toString());
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Goal of this subclass is to orchistrate the user into entering all necessary parameters
     */
    private static class StartupController{

        private static int depth;

        private static String URL;

        private static String startUpLanguage;




    }


}

