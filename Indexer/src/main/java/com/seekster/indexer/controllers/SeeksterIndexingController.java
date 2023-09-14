package com.seekster.indexer.controllers;

import com.seekster.indexer.rabbitmq.message.ContentMessage;
import com.seekster.indexer.services.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")

public class SeeksterIndexingController {

    @Autowired
    IndexingService indexingService;

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerIndexing(@RequestBody ContentMessage contentMessage) {
        try {
            indexingService.handleContentMessage(contentMessage);
            return new ResponseEntity<>("Indexing triggered successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while triggering indexing: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
