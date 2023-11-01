package com.seekster.indexer.api.services.impl;

import com.seekster.indexer.api.services.IndexDataService;
import com.seekster.indexer.indexer.indexwriter.IndexDataWriter;
import com.seekster.indexer.rabbitmq.message.ContentMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class IndexDataServiceImpl implements IndexDataService {

    @Value("${root.path}")
    private String rootPath;

    @Override
    public void writeIndexData(ContentMessage contentMessage) {
        log.info("Started execution of writeIndexData method");
        CompletableFuture.runAsync(
                () -> {
                    try {
                        IndexDataWriter indexDataWriter = new IndexDataWriter(rootPath);
                        indexDataWriter.write(contentMessage);
                    } catch (IOException e) {
                        log.error("error occurred while indexing document : {}", e.getMessage());
                    }
                });
    }
}
