package com.seekster.indexer.api.services;

import com.seekster.indexer.rabbitmq.message.ContentMessage;
import org.springframework.stereotype.Component;

@Component
public interface IndexDataService {

    void writeIndexData(ContentMessage contentMessage);

}
