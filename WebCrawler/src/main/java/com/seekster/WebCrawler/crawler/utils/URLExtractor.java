package com.seekster.WebCrawler.crawler.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

@Component
public class URLExtractor {
    public Set<URI> extractUrls(Document document, String baseUrl) {
        Set<URI> extractedUrls = new HashSet<>();

        Elements links = document.select("a[href]");

        for (Element link : links) {
            String href = link.attr("href");
            URI uri = createAbsoluteUri(baseUrl, href);

            if (uri != null) {
                extractedUrls.add(uri);
            }
        }
        return extractedUrls;
    }

    private URI createAbsoluteUri(String baseUrl, String href) {
        try {
            URI baseUri = new URI(baseUrl);
            URI resolvedUri = baseUri.resolve(href);

            // Filter out irrelevant URLs (e.g., images, stylesheets)
            if (isRelevantUri(resolvedUri)) {
                return resolvedUri;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isRelevantUri(URI uri) {
        // Implement logic to filter out irrelevant URLs
        // You can check for file extensions, specific paths, or other criteria
        // Example:
        // if (uri.getPath().endsWith(".html")) {
        //     return true;
        // }
        // return false;
        return true; // Placeholder
    }
}

