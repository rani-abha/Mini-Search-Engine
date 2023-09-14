package com.seekster.indexer;

import com.seekster.indexer.indexer.ContentTokenizer;
import com.seekster.indexer.indexer.InvertedIndex;
import com.seekster.indexer.rabbitmq.message.ContentMessage;
import com.seekster.indexer.services.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IndexerApplication {



	public static void main(String[] args) {
		SpringApplication.run(IndexerApplication.class, args);

		ContentMessage message = new ContentMessage();
		message.setUrl("https://example.com");
		message.setTitle("Example Title");
		message.setContent("This is the content of the message.");

		ContentTokenizer contentTokenizer =new ContentTokenizer();
		InvertedIndex invertedIndex = new InvertedIndex();

		IndexingService indexingService = new IndexingService(contentTokenizer,invertedIndex);
		indexingService.handleContentMessage(message);

	}

}
