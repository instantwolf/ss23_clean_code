package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URI;
import java.net.URISyntaxException;

public class ValidateURL {

    private static final String URL_REGEX =
            "^(http|https)://[a-zA-Z0-9]+([\\-\\.]{1}[a-zA-Z0-9]+)\\.[a-zA-Z]{2,5}(:[0-9]{1,5})?(\\/\\S)?$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static boolean isValidURL(String url) {
        String validUrl = url;
        try {
            URI uri = new URI(url);
            if (uri.getHost() == null) {
                String[] domainParts = url.split("\\.");
                if (domainParts.length > 1 && domainParts[domainParts.length - 1].length() <= 3) {
                    validUrl = "http://www." + url;
                } else {
                    validUrl = "http://" + url;
                }
            } else if (!url.startsWith("http://") && !url.startsWith("https://")) {
                validUrl = "http://" + url;
            }
        } catch (URISyntaxException e) {
            validUrl = "http://" + url;
        }
        Matcher matcher = URL_PATTERN.matcher(validUrl);
        return matcher.matches();
    }
}