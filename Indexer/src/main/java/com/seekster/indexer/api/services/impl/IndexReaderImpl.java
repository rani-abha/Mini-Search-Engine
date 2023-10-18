package com.seekster.indexer.api.services.impl;

import com.seekster.indexer.api.data.ReceivedIndexQuery;
import com.seekster.indexer.api.services.HandleIndexSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class IndexReaderImpl implements HandleIndexSearchRequest {

    @Override
    public void processRequestFor(ReceivedIndexQuery receivedIndexQuery) throws IOException {

    }
    @Override
    public void updateRequestStatusFor(ReceivedIndexQuery receivedIndexQuery, String queryRequestId) {

    }
}
