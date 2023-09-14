package com.seekster.indexer.indexer;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InvertedIndex {


    private final Map<String, Map<String, List<Integer>>> index;

    public InvertedIndex() {
        this.index = new HashMap<>();
    }

    public void addToIndex(String url, String title, String[] tokens) {
        for (int position = 0; position < tokens.length; position++) {
            String token = tokens[position];

            // Create or retrieve the token entry in the index
            index.putIfAbsent(token, new HashMap<>());
            Map<String, List<Integer>> tokenMap = index.get(token);

            // Create or retrieve the URL entry for the token
            tokenMap.putIfAbsent(url, new ArrayList<>());
            List<Integer> positions = tokenMap.get(url);

            // Add the position where the token appears
            positions.add(position);
        }
    }

    public Map<String, List<Integer>> getDocumentPositions(String token) {
        return index.getOrDefault(token, Collections.emptyMap());
    }

    public Set<String> getIndexedTokens() {
        return index.keySet();
    }
//    private final Directory indexDirectory;
//    private final StandardAnalyzer analyzer;
//    private final IndexWriterConfig indexWriterConfig;
//
//    public InvertedIndex() throws IOException {
//        // Define the directory to store the index
//        indexDirectory = FSDirectory.open(Paths.get("index_directory"));
//
//        // Configure the analyzer for tokenization
//        analyzer = new StandardAnalyzer();
//
//        // Configure the IndexWriter
//        indexWriterConfig = new IndexWriterConfig(analyzer);
//    }
//
//    public void addToIndex(String url, String[] tokens) throws IOException {
//        try (IndexWriter writer = new IndexWriter(indexDirectory, indexWriterConfig)) {
//            Document document = new Document();
//            document.add(new StringField("url", url, Field.Store.YES));
//
//            // Add tokens to the document
//            for (String token : tokens) {
//                document.add(new TextField("content", token, Field.Store.YES));
//            }
//
//            writer.addDocument(document);
//        }
//    }
//
//    public List<String> search(String query) throws IOException {
//        List<String> results = new ArrayList<>();
//
//        try (DirectoryReader reader = DirectoryReader.open(indexDirectory)) {
//            IndexSearcher searcher = new IndexSearcher(reader);
//            QueryParser parser = new QueryParser("content", analyzer);
//
//            Query luceneQuery = parser.parse(query);
//            TopDocs topDocs = searcher.search(luceneQuery, 10); // Return up to 10 results
//
//            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
//                Document document = searcher.doc(scoreDoc.doc);
//                results.add(document.get("url"));
//            }
//        } catch (org.apache.lucene.queryparser.classic.ParseException e) {
//            e.printStackTrace();
//        }
//
//        return results;
//    }
//
//    public void updateIndex(String url, String[] newTokens) throws IOException {
//        try (IndexWriter writer = new IndexWriter(indexDirectory, indexWriterConfig)) {
//            // Delete the document with the given URL
//            Term term = new Term("url", url);
//            writer.deleteDocuments(term);
//
//            // Add the document with updated content
//            Document document = new Document();
//            document.add(new StringField("url", url, Field.Store.YES));
//
//            // Add new tokens to the document
//            for (String token : newTokens) {
//                document.add(new TextField("content", token, Field.Store.YES));
//            }
//
//            writer.addDocument(document);
//        }
//    }
}
