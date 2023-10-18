package com.seekster.indexer.api.controllers;


import com.seekster.indexer.api.data.ReceivedIndexQuery;
import com.seekster.indexer.api.response.Response;
import com.seekster.indexer.api.services.impl.IndexReaderImpl;
import com.seekster.indexer.rabbitmq.message.ContentMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping(value = "/index-reader-handler")
@Slf4j
@Tag(name = "Index Data Reader Controller", description = "Read Index Data Using This Controller Api")
public class IndexerDataReaderController {

    @Autowired
    private IndexReaderImpl indexReaderService;

    @PostMapping(value = "/index-query/process", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> processIndexQuery(@RequestBody @Valid ReceivedIndexQuery receivedIndexQuery) throws IOException {
        indexReaderService.processRequestFor(receivedIndexQuery);
        return ResponseEntity.ok(
                Response.builder()
                        .responseTime(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Index search query submitted successfully!")
                        .method("IndexDataController.processIndexQuery")
                        .executionMessage("Implemented business logic of service class method")
                        .build()
        );
    }
}
