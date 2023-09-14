package com.seekster.indexer.rabbitmq;

public class RabbitMqConstants {
    public static final String DEAD_LETTER_QUEUE = "deadLetter.queue";
    public static final String DEAD_LETTER_EXCHANGE = "deadLetterExchange";
    public static final String DEAD_LETTER_KEY = "deadLetterKey";
    public static final String SEEKSTER_EXCHANGE = "seekster.exchange";

    //For test
    public static final String TEST_RECEIVE = "seekster.test.receive";
    // for test
    public static final String TEST_SEND = "seekster.test.send";

    public static final String QUEUE_CRAWLER_RECIEVE = "seekster.crawler.queue";

    public static final String QUEUE_CRAWLER_SEND = "seekster.crawler.send.queue";
}
