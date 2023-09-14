package com.seekster.indexer.rabbitmq;

import com.seekster.indexer.rabbitmq.message.ContentMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private static Log log = LogFactory.getLog(MessageSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String send(CustomMessage message,String queueName)
    {
        rabbitTemplate.convertAndSend(RabbitMqConstants.SEEKSTER_EXCHANGE, queueName, message);
        log.info("Send msg  "+ message);
        return "Message Published";
    }

    public String send(ContentMessage message, String queueName) {

        rabbitTemplate.convertAndSend(RabbitMqConstants.SEEKSTER_EXCHANGE, queueName, message);
        log.info("Send msg  " + message);
        return "Message Published";
    };
}
