package com.seekster.indexer.api.controllers;

import com.seekster.indexer.api.response.Response;
import com.seekster.indexer.api.services.IndexDataService;
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


import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping(value = "/index-writer-handler")
@Slf4j
@Tag(name = "Index Data Controller", description = "Index Data Using This Controller Api")
public class IndexerDataWriterController {

    @Autowired
    IndexDataService indexStorageService;
    @PostMapping(value = "/index-data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> indexData(@RequestBody @Valid ContentMessage packetData) {
        try {
            indexStorageService.writeIndexData(packetData);
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Index Data Saved successfully!")
                            .method("IndexDataController.indexData")
                            .executionMessage("Implemented business logic of service class method").build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.FORBIDDEN)
                            .statusCode(HttpStatus.FORBIDDEN.value())
                            .message("Index Data Saved UnSuccessfully!")
                            .method("IndexDataController.indexData")
                            .executionMessage("Implemented business logic of service class method")
                            .build()
            );
        }
    }
}
