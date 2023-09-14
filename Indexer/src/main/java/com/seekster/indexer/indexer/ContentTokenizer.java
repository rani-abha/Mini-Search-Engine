package com.seekster.indexer.indexer;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

@Component
public class ContentTokenizer {
    // Define a list of stop words to be removed from tokens (customize as needed)
    private static final List<String> stopWords = new ArrayList<>();

    static {
        // Add common English stop words
        stopWords.add("a");
        stopWords.add("an");
        stopWords.add("the");
        // Add more stop words as needed
    }

    // Regular expression pattern to split content into words
    private static final Pattern wordPattern = Pattern.compile("\\W+");

    public String[] tokenize(String content) {
        // Split content into words
        String[] words = wordPattern.split(content.toLowerCase());

        // Filter out stop words and empty strings
        List<String> filteredWords = new ArrayList<>();
        for (String word : words) {
            if (!stopWords.contains(word) && !word.isEmpty()) {
                filteredWords.add(word);
            }
        }

        // Convert the filtered words to an array
        return filteredWords.toArray(new String[0]);
    }
//    public String[] tokenizeAndPreprocess(String content) {
//        // Tokenize the content using StringTokenizer
//        String[] tokens = tokenize(content);
//
//        // Apply preprocessing steps like lowercasing, removing punctuation, and filtering out stop words
//        tokens = preprocess(tokens);
//
//        return tokens;
//    }
//
//    private String[] tokenize(String content) {
//        // Tokenize the content using StringTokenizer
//        List<String> tokenList = new ArrayList<>();
//        StringTokenizer tokenizer = new StringTokenizer(content);
//
//        while (tokenizer.hasMoreTokens()) {
//            String token = tokenizer.nextToken();
//            tokenList.add(token);
//        }
//
//        return tokenList.toArray(new String[0]);
//    }
//
//    private String[] preprocess(String[] tokens) {
//        // Apply preprocessing steps
//        List<String> preprocessedTokens = new ArrayList<>();
//
//        for (String token : tokens) {
//            // Lowercase the token
//            token = token.toLowerCase();
//
//            // Remove punctuation (you can customize this)
//            token = token.replaceAll("[^a-zA-Z0-9]", "");
//
//            // Filter out common words (stop words) - you need to define your stop word list
//            if (!isStopWord(token)) {
//                preprocessedTokens.add(token);
//            }
//        }
//
//        return preprocessedTokens.toArray(new String[0]);
//    }
//
//    private boolean isStopWord(String token) {
//        // Implement logic to check if a token is a stop word
//        // You can maintain a list of stop words and check if the token is in that list
//        return false; // Replace with your implementation
//    }
}

