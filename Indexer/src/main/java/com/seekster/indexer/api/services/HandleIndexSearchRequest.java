package com.seekster.indexer.api.services;

import com.seekster.indexer.api.data.ReceivedIndexQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface HandleIndexSearchRequest {
    void processRequestFor(ReceivedIndexQuery receivedIndexQuery) throws IOException;

    void updateRequestStatusFor(ReceivedIndexQuery receivedIndexQuery, String queryRequestId);

}
