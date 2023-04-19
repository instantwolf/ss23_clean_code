package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TranslateAPIRequestHandler
{
private static final String apiKey = "d79c5b0869msh51b9569b6ad2468p10960ejsnd234da18ad6b";

private static final String apiHostname = "text-translator2.p.rapidapi.com";


public static void tesRequest(){
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://text-translator2.p.rapidapi.com/getLanguages"))
            .header("X-RapidAPI-Key", "d79c5b0869msh51b9569b6ad2468p10960ejsnd234da18ad6b")
            .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

    HttpResponse<String> response = null;
    try {
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
        System.out.println("TRANSLATEAPI::testRequest(): IOException aufgetreten");
        throw new RuntimeException(e);
    } catch (InterruptedException e) {
        System.out.println("TRANSLATEAPI::testRequest(): Runtimeexception aufgetreten");
        throw new RuntimeException(e);
    }
    System.out.println(response.body());

}


public static void translateRequest(String textToTranslate, String targetLanguageCode){

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
            .header("content-type", "application/x-www-form-urlencoded")
            .header("X-RapidAPI-Key", "d79c5b0869msh51b9569b6ad2468p10960ejsnd234da18ad6b")
            .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
            .method("POST", HttpRequest.BodyPublishers.ofString("source_language=auto" +
                    "&target_language="+targetLanguageCode+"&text="+textToTranslate))
            .build();

    try {
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    } catch (IOException e) {
        System.out.println("TRANSLATEAPI::translateRequest(): IOException aufgetreten");
        throw new RuntimeException(e);
    } catch (InterruptedException e) {
        System.out.println("TRANSLATEAPI::translateRequest(): InterruptedException aufgetreten");
        throw new RuntimeException(e);
    }

}



}
