package com.seekster.indexer.api.services.impl;

import com.seekster.indexer.api.data.ReceivedIndexQuery;
import com.seekster.indexer.api.services.HandleIndexSearchRequest;
import com.seekster.indexer.indexer.indexreader.IndexDataReader;
import com.seekster.indexer.rabbitmq.message.ContentMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class IndexReaderImpl implements HandleIndexSearchRequest {

    @Value("${root.path}")
    private String rootPath;

    @Override
    public void processRequestFor(ReceivedIndexQuery receivedIndexQuery) throws IOException {

    }
    @Override
    public void updateRequestStatusFor(ReceivedIndexQuery receivedIndexQuery, String queryRequestId) {

    }
    public List<ContentMessage> processQueryUsingQueryParser(String queryText, int noOfTopDoc) throws IOException {
        log.info("Started execution of processQueryUsingQueryParser method");
        IndexDataReader indexDataReader = new IndexDataReader(rootPath);
        return indexDataReader.read(queryText, noOfTopDoc);
    }
}
