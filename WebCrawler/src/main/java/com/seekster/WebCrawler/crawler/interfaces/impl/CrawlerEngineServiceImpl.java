package com.seekster.WebCrawler.crawler.interfaces.impl;

import com.seekster.WebCrawler.crawler.interfaces.CrawlerEngineService;
import com.seekster.WebCrawler.rabbitmq.MessageSender;
import com.seekster.WebCrawler.rabbitmq.RabbitMqConstants;
import com.seekster.WebCrawler.rabbitmq.message.ContentMessage;
import com.seekster.WebCrawler.registry.ApplicationContextProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class CrawlerEngineServiceImpl implements CrawlerEngineService {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Set<URI> visitedUrls = new HashSet<>();
    MessageSender messageSender = ApplicationContextProvider.getApplicationContext().getBean(MessageSender.class);

    public void crawl(String startUrl, int maxDepth) {
        try {
            URI startUri = new URI(startUrl);
            crawlUrl(startUri, 0, maxDepth);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void crawlUrl(URI url, int depth, int maxDepth) {

        if (depth > maxDepth || visitedUrls.contains(url)) {
            return;
        }
        visitedUrls.add(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        responseFuture.thenAccept(response -> {
            int statusCode = response.statusCode();

            if (statusCode == 200) {

                String htmlContent = response.body();

                // Parse and process the HTML content here
                // You can use a library like Jsoup to parse the HTML content
                Document doc = Jsoup.parse(htmlContent);
                // Extract and clean up text
                String cleanedText = extractTextWithoutWhitespace(doc);

                ContentMessage contentMessage = new ContentMessage();
                contentMessage.setUrl(url.getPath());
                contentMessage.setTitle(doc.title());
                contentMessage.setContent(cleanedText);
                System.out.println(contentMessage);
                messageSender.send(contentMessage, RabbitMqConstants.QUEUE_CRAWLER_CONTENT_SEND);

                // Print the cleaned text
                System.out.println(doc.title());
            } else if (statusCode == 301 || statusCode == 302) {
                crawlUrl(url, depth+1, maxDepth);
            } else {
                // Handle non-success HTTP status codes
                System.err.println("Failed to fetch URL: " + url + ". Status code: " + statusCode);
            }
        });
        // Handle response here (e.g., parse HTML content, store data, etc.)

        // Extract links from the HTML content and recursively crawl them
        // Set<URI> links = extractLinksFromHtml(response.body());
//            for (URI link : links) {
//                crawlUrl(link, depth + 1, maxDepth);
//            }
    }

    public static String extractTextWithoutWhitespace(Node node) {
        StringBuilder sb = new StringBuilder();

        // Traverse the node and its children
        for (Node child : node.childNodes()) {
            if (child instanceof TextNode) {
                TextNode textNode = (TextNode) child;
                String text = textNode.text().trim(); // Remove leading and trailing whitespace

                // Only add non-empty text
                if (!text.isEmpty()) {
                    sb.append(text).append(" "); // Add a space between different text nodes
                }
            } else {
                // Recursively process child nodes
                String childText = extractTextWithoutWhitespace(child);
                if (!childText.isEmpty()) {
                    sb.append(childText).append(" ");
                }
            }
        }

        return sb.toString().trim(); // Remove trailing whitespace
    }
}
