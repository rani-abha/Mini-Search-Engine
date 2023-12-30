package com.seekster.indexer.rabbitmq;

import com.seekster.indexer.api.services.IndexDataService;
import com.seekster.indexer.api.services.impl.IndexDataServiceImpl;
import com.seekster.indexer.rabbitmq.message.ContentMessage;
import com.seekster.indexer.api.services.IndexingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
    private static Log log = LogFactory.getLog(MessageReceiver.class);
    @Autowired
    private IndexDataServiceImpl indexingService;

    @RabbitListener(queues = RabbitMqConstants.TEST_RECEIVE)
    public void receiveMessageCrawler(CustomMessage message) {
        log.info("Received Message From RabbitMq receiveMessage : <" + message + ">");
        System.out.println("Message : " + message);
        try {
            System.out.println("success..!!");
        } catch (Exception e) {
            log.info("receiveMessageCrawler Failed :" + e.getMessage());
        }
    }

    @RabbitListener(queues = RabbitMqConstants.QUEUE_CRAWLER_RECIEVE)
    public void receiveMessageFromCrawler(ContentMessage message) {
        log.info("Received Staff User Message From RabbitMq is : <" + message + ">");
        try {
            indexingService.writeIndexData(message);
        } catch (Exception e) {
            log.info("receiveMessageFromCrawler :" + e.getMessage());
        }
    }
}
