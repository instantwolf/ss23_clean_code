package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
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
}