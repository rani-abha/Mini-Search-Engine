package com.seekster.WebCrawler.rabbitmq;

import com.seekster.WebCrawler.rabbitmq.message.ContentMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    private static final Log log = LogFactory.getLog(MessageSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String send(ContentMessage message, String queueName) {

        rabbitTemplate.convertAndSend(RabbitMqConstants.SEARCH_EXCHANGE, queueName, message);
        log.info("Send msg  " + message);
        return "Message Published";
    }
}
