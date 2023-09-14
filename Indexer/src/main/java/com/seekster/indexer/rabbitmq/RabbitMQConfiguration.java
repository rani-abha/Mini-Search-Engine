package com.seekster.indexer.rabbitmq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;



@Configuration
public class RabbitMQConfiguration {

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(RabbitMqConstants.DEAD_LETTER_QUEUE).build();
    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(RabbitMqConstants.DEAD_LETTER_EXCHANGE);
    }

    @Bean
    Binding DLQbinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(RabbitMqConstants.DEAD_LETTER_KEY);
    }

    @Bean
    public DirectExchange  adoptExchange() {
        return new DirectExchange (RabbitMqConstants.SEEKSTER_EXCHANGE);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    /**
     * For testing purpose only
     * @return
     */
    @Bean
    public Queue createTestReceiveQueue() {
        return QueueBuilder.durable(RabbitMqConstants.TEST_RECEIVE).withArgument("x-dead-letter-exchange", RabbitMqConstants.DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", RabbitMqConstants.DEAD_LETTER_KEY).build();
    }

    @Bean
    public Binding createTestReceiveBinding() {
        return BindingBuilder.bind(createTestReceiveQueue()).to(adoptExchange()).withQueueName();
    }

    @Bean
    public Queue createTestSendQueue() {
        return QueueBuilder.durable(RabbitMqConstants.TEST_SEND).withArgument("x-dead-letter-exchange", RabbitMqConstants.DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", RabbitMqConstants.DEAD_LETTER_KEY).build();
    }

    @Bean
    public Binding createTestSendBinding() {
        return BindingBuilder.bind(createTestReceiveQueue()).to(adoptExchange()).withQueueName();
    }


    @Bean
    public Queue receiveMessageFromCrawler() {
        return QueueBuilder.durable(RabbitMqConstants.QUEUE_CRAWLER_RECIEVE)
                .withArgument("x-dead-letter-exchange", RabbitMqConstants.DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", RabbitMqConstants.DEAD_LETTER_KEY).build();
    }

    @Bean
    public Binding saveACLQueueBinding() {
        return BindingBuilder.bind(receiveMessageFromCrawler()).to(adoptExchange()).withQueueName();
    }

    @Bean
    public Queue sendMessageToCrawler() {
        return QueueBuilder.durable(RabbitMqConstants.QUEUE_CRAWLER_SEND)
                .withArgument("x-dead-letter-exchange", RabbitMqConstants.DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", RabbitMqConstants.DEAD_LETTER_KEY).build();
    }

    @Bean
    public Binding sendIndexQueueBinding() {
        return BindingBuilder.bind(sendMessageToCrawler()).to(adoptExchange()).withQueueName();
    }
}
