package com.seekster.indexer.indexer.indexreader;

import com.seekster.indexer.api.data.IndexSearchQuery;
import com.seekster.indexer.indexer.indexstorage.IndexUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.concurrent.Callable;


@Slf4j
public class IndexDataReader implements Callable<Boolean> {
    private final IndexSearchQuery indexSearchQuery;

    //TODO: Creating Queues
//    private final ConcurrentQueue<Document> resultDocs;

    private final String index;

    public IndexDataReader(IndexSearchQuery indexSearchQuery, String index
    ) {
        this.indexSearchQuery = indexSearchQuery;
        this.index = index;
//        this.resultDocs = resultDocs;
    }
    @Override
    public Boolean call() {
        try {
            read(indexSearchQuery);
            return true;
        } catch (IOException ioException) {
            log.error("A index search process error occurred, with the following cause: [IOException, reason is {}].", ioException.getMessage());
            return true; // task has been completed
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("A index search process error occurred, with the following cause: [IllegalArgumentException, reason is {}].", illegalArgumentException.getMessage());
            return true; // task has been completed
        }
    }

    public void read(IndexSearchQuery indexSearchQuery) throws IOException {
        log.info("Started execution of reading utility of index reader job...");
        indexSearchQuery.setIndex(index);
        // Create the index directory
        Directory directory = IndexUtils.openDirectory(indexSearchQuery.getIndex());
        // Search based on fields
        org.apache.lucene.index.IndexReader reader = DirectoryReader.open(directory);

        //TODO: Creating Factory
//        IndexQueryFactory queryFactory = new IndexQueryFactory();
//        IndexQuery indexQuery = queryFactory.submitIndexQuery(indexSearchQuery.getQuery().getType());
//        indexQuery.execute(indexSearchQuery, reader, resultDocs);
    }

}
