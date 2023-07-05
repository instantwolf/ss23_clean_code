package edu.aau.cleancode.webcrawler.translator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.aau.cleancode.webcrawler.parser.HtmlHeading;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TranslateAPIRequestHandler
{
private static final String apiKey = "d79c5b0869msh51b9569b6ad2468p10960ejsnd234da18ad6b";

private static final String apiHostname = "text-translator2.p.rapidapi.com";


public static String translateRequest(String textToTranslate, String targetLanguageCode){
    TranslateAPIRequestHandler handler = new TranslateAPIRequestHandler();
    try {
        HttpRequest request = buildRequest(targetLanguageCode,textToTranslate);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
         return getDataFromResponse(response);
    } catch (IOException e) {
        System.out.println("TRANSLATEAPI::translateRequest(): IOException aufgetreten");
        throw new RuntimeException(e);
    } catch (InterruptedException e) {
        System.out.println("TRANSLATEAPI::translateRequest(): InterruptedException aufgetreten");
        throw new RuntimeException(e);
    }
}

public static List<HtmlHeading> translateHeadings(List<HtmlHeading> headings, String targetLanguage){
    List<HtmlHeading> translatedHeadings;
    translatedHeadings = headings.stream().map(x ->  translateHeading(x, targetLanguage)).collect(Collectors.toList());
    return translatedHeadings;
}

public static HtmlHeading translateHeading(HtmlHeading heading, String targetLanguage){
    String translatedHeading = translateRequest(heading.getContent(), targetLanguage);
    return heading.setContent(translatedHeading);
}

    private static HttpRequest buildRequest(String targetLanguageCode, String textToTranslate){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "d79c5b0869msh51b9569b6ad2468p10960ejsnd234da18ad6b")
                .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("source_language=auto" +
                        "&target_language="+targetLanguageCode+"&text="+textToTranslate))
                .build();
        return request;
    }


private static String getDataFromResponse(HttpResponse<String> response){
// Parse the response body as a JSON object using Gson
    JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

    // Extract the translated text from the JSON object
    return jsonObject.getAsJsonObject("data").get("translatedText").getAsString();}


}




