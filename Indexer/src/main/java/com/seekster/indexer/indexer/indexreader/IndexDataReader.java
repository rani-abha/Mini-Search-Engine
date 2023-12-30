package com.seekster.indexer.indexer.indexreader;

import com.seekster.indexer.api.data.IndexSearchQuery;
import com.seekster.indexer.indexer.indexstorage.IndexUtils;
import com.seekster.indexer.rabbitmq.message.ContentMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


@Slf4j
@Getter
@Setter
public class IndexDataReader {

    private Directory directory;
    private String indexPath;
    private DirectoryReader directoryReader;
    private IndexSearcher indexSearcher;
    private QueryParser queryParser;

    public IndexDataReader(String indexPath) throws IOException {
        this.directory = FSDirectory.open(new File(indexPath).toPath());
        this.indexPath = indexPath;
        this.directoryReader = DirectoryReader.open(directory);
        this.indexSearcher = new IndexSearcher(directoryReader);
        this.queryParser = new QueryParser("content", new StandardAnalyzer());
    }


    public List<ContentMessage> read(String queryText, int noOfTopDoc) throws IOException {
        List<ContentMessage> documents = new ArrayList<>();
        try {
            // Parse the user's query
            Query query = queryParser.parse(queryText);
            // Execute the query and get the top 10 results
            TopDocs topDocs = indexSearcher.search(query, noOfTopDoc);
            // Iterate over the search results and print the documents
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document resultDoc = indexSearcher.doc(scoreDoc.doc);
                documents.add(new ContentMessage(resultDoc.get("url"), resultDoc.get("title"), resultDoc.get("content")));
            }
        } catch (ParseException e) {
            log.error("error occurred while reading index : {}", e.getMessage());
        }

        // Close the reader and directory
        directoryReader.close();
        directory.close();

        return documents;
    }

}
