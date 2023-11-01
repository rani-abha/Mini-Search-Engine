package com.seekster.indexer;

import com.seekster.indexer.indexer.ContentTokenizer;
import com.seekster.indexer.indexer.InvertedIndex;
import com.seekster.indexer.rabbitmq.message.ContentMessage;
import com.seekster.indexer.api.services.IndexingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IndexerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndexerApplication.class, args);
    }

}
