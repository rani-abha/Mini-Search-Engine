package com.seekster.WebCrawler.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
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
    public DirectExchange searchExchange() {
        return new DirectExchange(RabbitMqConstants.SEARCH_EXCHANGE);
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

    @Bean
    public Queue sendCrawlerContent() {
        return QueueBuilder.durable(RabbitMqConstants.QUEUE_CRAWLER_CONTENT_SEND)
                .withArgument("x-dead-letter-exchange", RabbitMqConstants.DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", RabbitMqConstants.DEAD_LETTER_KEY).build();
    }

    @Bean
    public Binding sendCrawlerContentQueueBinding() {
        return BindingBuilder.bind(sendCrawlerContent()).to(searchExchange()).withQueueName();
    }

}
