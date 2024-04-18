package org.quotely;

import java.io.IOException;

import java.util.logging.Logger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import com.google.gson.Gson;

public class Quote {
    public String quoteText;
    public String quoteAuthor;
    public String senderName;
    public String senderLink;
    public String quoteLink;

    public String getQuoteText() {
        return quoteText;
    }
    public String getQuoteAuthor() {
        if (quoteAuthor == "" ){
            return "unknown";
        } else {
            return quoteAuthor;
        }
    }

    public String toString() {
        String s = new StringBuilder()
        .append("Author: ")
        .append(getQuoteAuthor())
        .append("\n")
        .append(quoteText)
        .toString();
        return s;
    }

    public static Quote NewQuote(String language) throws IOException, InterruptedException{
        // TODO: configurable api endpoint, formats. (mainly for testing/mocking)
        URI uri = URI.create("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=" + language);
        // TODO: change these to config variables instead of hardcoding. 
        HttpClient client = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .followRedirects(Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            .build();
         
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("Accept", "application/json")
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        // TODO: implement retries, logging, etc. All the fun stuff. 
        if (response.statusCode() == 200 ){
            Gson gson = new Gson();
            Quote quote = gson.fromJson(response.body(), Quote.class);
            return quote;
        } else {
            throw new IOException("unsupported http status code: " + Integer.toString(response.statusCode()));
        }
    }
}
