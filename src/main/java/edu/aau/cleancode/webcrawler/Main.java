package edu.aau.cleancode.webcrawler;

import edu.aau.cleancode.webcrawler.markdown.MarkDownStringGenerator;
import edu.aau.cleancode.webcrawler.parser.HtmlParserAdapter;
import edu.aau.cleancode.webcrawler.parser.JsoupHtmlParserAdapter;
import org.jsoup.internal.StringUtil;

import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static final List<String> allowedLanguages = new ArrayList<>(
            Arrays.asList("af","sq","am","ar","hy","az","eu","be","bn","bs","bg","ca",
                    "ceb", "ny", "zh-CN","zh-TW","co","hr","cs","da","nl","en","eo",
                    "et","tl", "fi", "fr", "fy", "gl", "ka", "de", "el", "gu",
                    "ht", "ha", "haw", "iw", "hi", "hmn", "hu", "is", "ig", "id", "ga",
                    "it", "ja", "jw", "kn", "kk", "km", "rw", "ko", "ku", "ky", "lo",
                    "la", "lv", "lt", "lb", "mk", "mg", "ms", "ml", "mt", "mi", "mr",
                    "mn", "my", "ne", "no","or","ps", "fa","pl","pt","pa","ro","ro","ru",
                    "sm","gd","sr", "st", "sn", "sd","si", "sk", "sl", "so", "es", "su",
                    "sw", "sv", "tg", "ta", "tt", "te", "th", "tr", "tk", "uk", "ur",
                    "ug", "uz", "vi","cy", "xh", "yi", "yo", "zu", "he", "zh"));

    public static void main(String[] args) {
        // Press ⌥⏎ with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        System.out.println("Enter your URL like: http://www.example.com \n If you want to crawl multiple Pages in parallel, " +
                "enter URLs like this: [URL1];[URL2]");

        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        input = readInputLine(reader);

        List<String> startUpUrls = new ArrayList<>();

        if(input.contains(";")){
             startUpUrls.addAll(Arrays.asList(input.split(";")));
        }else{
            startUpUrls.add(input);
        }


        for (String url: startUpUrls
             ) {
            if(!ValidateURL.isValidURL(url)){
                System.out.println("Wrong URL type, aborting execution");
                return;
            }
        }

        //fetch targetLanguage
        System.out.println("Enter the target language the headings shall be translated into. \n" +
                "Enter the target language as 2-digit code (ISO 639-1). \n" +
                "Examples: \"de\",\"en\",\"fr\"..");

        input = readInputLine(reader);
        final String targetLanguage = input;

        if(!allowedLanguages.stream().anyMatch(x -> x.equals(targetLanguage))){
            System.out.println("Invalid language provided. Please enter one of the following languages:");
            allowedLanguages.stream().forEach(System.out::print);
            return;
        }

        System.out.println("Enter your depth");
        input = readInputLine(reader);


        if(!isNumeric(input) || Integer.parseInt(input) < 0){
            System.out.println("Invalid depth, please provide a positive  number");
            return;
        }


        int depth = Integer.parseInt(input);
        List<CrawledPage> resultSet = new ArrayList<>();
        JsoupHtmlParserAdapter adapter = new JsoupHtmlParserAdapter();

        if(startUpUrls.size() > 1){
            ParallelCrawler crawler = new ParallelCrawler(startUpUrls,targetLanguage,depth,adapter);
            try{
                resultSet = crawler.crawl();
            }catch (InterruptedException e){

              System.out.println("The parallel crawler was interrupted during execution Find the details below: \n \n");
              System.out.println(e.getMessage());
            }catch (ExecutionException e){
                System.out.println("Something went wrong during retrieval of the results of the crawler threads: \n \n");
                System.out.println(e.getMessage());
            }
        }else{
            WebCrawler crawler = new WebCrawler(startUpUrls.get(0),targetLanguage,depth,adapter);
            resultSet = crawler.crawl();
        }

        resultSet.stream().forEach(x -> System.out.println(x.toString()));

       //TODO: Implement Printer class
        //get MarkdownString
        MarkDownStringGenerator generator = new MarkDownStringGenerator();
        String markDown = generator.generateMarkDown(startUpUrls,depth,"auto-detect",targetLanguage,resultSet);

        //print to file
        String filePath = "./results.md";
        FileHandler handler = new FileHandler( filePath,false);
        try{
            handler.write(markDown);
        }catch (IOException e){
            System.out.println("Something went wrong while writing to the file "+filePath );
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


    /**
    public static void main(String[] args) {
        try{
           JsoupHtmlParserAdapter adapter = new JsoupHtmlParserAdapter();

            List<HtmlHeading> headings = adapter.getHeadings("https://en.wikipedia.org/wiki/Web_crawler");

            System.out.println(headings.toString());

            List<String> bla = adapter.getLinks("https://en.wikipedia.org/wiki/Web_crawler");
            System.out.println(bla.toString());



            TranslateAPIRequestHandler translateAPIRequestHandler = new TranslateAPIRequestHandler();
            String translation = TranslateAPIRequestHandler.translateRequest("Guten Tag!","en");
            System.out.println(translation);

            HtmlParserAdapter parser = new JsoupHtmlParserAdapter();
            String url1 = "https://example.com/";

            WebCrawler crawler = new WebCrawler(url1,
                    "de",0,parser);
            crawler.crawl();
            List<CrawledPage> results = crawler.getResults();

            String url2 = "https://moodle.aau.at/login/index.php";

            WebCrawler crawler2 = new WebCrawler(url2, "de", 0, parser);
            crawler2.crawl();
            List<CrawledPage> results2 = crawler2.getResults();

            ArrayList<String> urls = new ArrayList<>();
            urls.add(url1);
            urls.add(url2);

            ParallelCrawler pcrawler = new ParallelCrawler(urls, "de", 0, parser);
            List<CrawledPage> resultsPar = pcrawler.crawl();
            int i = 1;
            System.out.println("112");


        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

    }
     */

    private static String readInputLine(BufferedReader reader) {
        String var;
        try {
            var = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input variable: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return var;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}

