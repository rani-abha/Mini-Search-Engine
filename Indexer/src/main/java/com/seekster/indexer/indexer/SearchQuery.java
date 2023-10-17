package com.seekster.indexer.indexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class SearchQuery {

    private String query;
    private final ContentTokenizer contentTokenizer;
    private final InvertedIndex invertedIndex;

    @Autowired
    public SearchQuery(ContentTokenizer contentTokenizer, InvertedIndex invertedIndex) {
        this.contentTokenizer = contentTokenizer;
        this.invertedIndex = invertedIndex;
    }

    public Map<String, List<Integer>> search() {
        // Check if the query is null or empty, return an empty result
        if (query == null || query.isEmpty()) {
            return Collections.emptyMap();
        }

        // Tokenize the query string
        String[] queryTokens = contentTokenizer.tokenize(query);

        System.out.println("-----------query---------" + queryTokens);

        // Initialize a map to store search results (URLs with positions)
        Map<String, List<Integer>> searchResults = new HashMap<>();

        // Search for each query token in the inverted index
        for (String queryToken : queryTokens) {
            Map<String, List<Integer>> tokenInfo = invertedIndex.getDocumentPositions(queryToken);

            // If the token is found in the index, add URLs with positions to the search results
            if (tokenInfo != null) {
                for (Map.Entry<String, List<Integer>> entry : tokenInfo.entrySet()) {
                    String url = entry.getKey();
                    List<Integer> positions = entry.getValue();
//
//                    String title = getTitleFromURL(url);
//                    String content = getContentSnippetFromURL(url, positions);
//
//                    // Create a map to store title and content snippet
//                    Map<String, String> resultInfo = new HashMap<>();
//                    resultInfo.put("title", title);
//                    resultInfo.put("contentSnippet", content);
                    // Add URL and positions to the search results
                    searchResults.put(url, positions);
                    System.out.println("url fired by query "+url);
                }
            }
        }

        // Optionally, you can perform additional filtering or ranking of search results here
        System.out.println("----------------results----" + searchResults);
        return searchResults;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
