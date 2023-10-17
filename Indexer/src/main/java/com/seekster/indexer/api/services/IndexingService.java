package com.seekster.indexer.api.services;

import com.seekster.indexer.indexer.ContentTokenizer;
import com.seekster.indexer.indexer.InvertedIndex;
import com.seekster.indexer.indexer.SearchQuery;
import com.seekster.indexer.rabbitmq.MessageReceiver;
import com.seekster.indexer.rabbitmq.MessageSender;
import com.seekster.indexer.rabbitmq.RabbitMqConstants;
import com.seekster.indexer.rabbitmq.message.ContentMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IndexingService {

//    @Autowired
//    MessageSender messageSender;

//    @Autowired
//    MessageReceiver messageReceiver;
    private final ContentTokenizer contentTokenizer;
    private final InvertedIndex invertedIndex;

    @Autowired
    public IndexingService(ContentTokenizer contentTokenizer, InvertedIndex invertedIndex) {
        this.contentTokenizer = contentTokenizer;
        this.invertedIndex = invertedIndex;

    }

    public void handleContentMessage(ContentMessage contentMessage) {
        String url = contentMessage.getUrl();
        String title = contentMessage.getTitle();
        String content = contentMessage.getContent();

        // Tokenize the content
        String[] tokens = contentTokenizer.tokenize(content+title);

        System.out.println("URL: " + url);
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);

        // Update the inverted index
        invertedIndex.addToIndex(url, title, tokens);

        System.out.println("Added to the inverted index.");
//messageSender.send( url, RabbitMqConstants.QUEUE_CRAWLER_SEND);
        // Optionally, you can log or perform other actions here
    }


}
