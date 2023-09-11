package com.seekster.WebCrawler.services.impl;

import com.seekster.WebCrawler.services.CrawlerEngineService;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class CrawlerEngineServiceImpl implements CrawlerEngineService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void crawl(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();

            CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            responseFuture.thenAccept(response -> {
                int statusCode = response.statusCode();
                if (statusCode == 200) {
                    String htmlContent = response.body();

                    System.out.println();

                    System.out.println(htmlContent);

                    // Parse and process the HTML content here
                    // You can use a library like Jsoup to parse the HTML content
                    // For example: Document doc = Jsoup.parse(htmlContent);
                } else {
                    // Handle non-success HTTP status codes
                    System.err.println("Failed to fetch URL: " + url + ". Status code: " + statusCode);
                }
            });

        } catch (URISyntaxException e) {
            // Handle exceptions, e.g., network errors or timeouts
            e.printStackTrace();
        }
    }
}
