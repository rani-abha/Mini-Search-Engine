package com.seekster.indexer.api.services.impl;

import com.seekster.indexer.api.data.RootPathConfiguration;
import com.seekster.indexer.api.services.IndexDataService;
import com.seekster.indexer.rabbitmq.message.ContentMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IndexDataServiceImpl implements IndexDataService {

    @Autowired
    private RootPathConfiguration rootPathConfiguration;

    private String rootPath;

    @Override
    public void writeIndexData(ContentMessage contentMessage) {
        rootPath = rootPathConfiguration.getRootPath();

    }
}
