package com.seekster.indexer.api.services;

import com.seekster.indexer.api.data.ReceivedIndexQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface HandleIndexSearchRequest {

    //TODO: GenericExceptions
    void processRequestFor(ReceivedIndexQuery receivedIndexQuery) throws IOException;

    //TODO: updateRequestStatusFor is implemented using thread
    void updateRequestStatusFor(ReceivedIndexQuery receivedIndexQuery, String queryRequestId);

}
