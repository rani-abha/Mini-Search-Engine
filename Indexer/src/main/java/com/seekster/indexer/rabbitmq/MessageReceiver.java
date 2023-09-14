package com.seekster.indexer.rabbitmq;

import com.seekster.indexer.rabbitmq.message.ContentMessage;
import com.seekster.indexer.services.IndexingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
    private static Log log = LogFactory.getLog(MessageReceiver.class);
 @Autowired
 private IndexingService indexingService;
//    @Autowired
//    private TacacsStaffUserServiceImpl tacacsStaffUserService;

    @RabbitListener(queues = RabbitMqConstants.TEST_RECEIVE)
    public void receiveMessageCrawler(CustomMessage message) {
        log.info("Received Message From RabbitMq receiveMessage : <" + message + ">");
        System.out.println("Message : " + message);
        try {
            System.out.println("success..!!");
        }
        catch(Exception e) {
            log.info("receiveMessageCrawler Failed :"+e.getMessage());
        }
    }

    @RabbitListener(queues = RabbitMqConstants.QUEUE_CRAWLER_RECIEVE)
    public void receiveMessageFromCrawler(ContentMessage message){
        log.info("Received Staff User Message From RabbitMq is : <" + message + ">");
        try {
            indexingService.handleContentMessage(message);
//            tacacsStaffUserService.createTacacsStaffUser(message);
        } catch (Exception e) {
            log.info("receiveMessageFromCrawler :" + e.getMessage());
        }
    }
    }

//    @RabbitListener(queues = RabbitMqConstants.QUEUE_STAFF_SAVE_USER_SEND)
//    public void receiveMessageStaffManagementFromAPIGW(UserMessage message) {
//        log.info("Received Staff User Message From RabbitMq is : <" + message + ">");
//        try {
//            tacacsStaffUserService.createTacacsStaffUser(message);
//        } catch (Exception e) {
//            log.info("receiveMessageCustomerApigw Failed :" + e.getMessage());
//        }
//    }

