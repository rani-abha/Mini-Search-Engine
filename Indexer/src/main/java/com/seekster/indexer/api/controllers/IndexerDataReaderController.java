package com.seekster.indexer.api.controllers;

import com.seekster.indexer.api.response.Response;
import com.seekster.indexer.api.services.impl.IndexReaderImpl;
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
import java.util.Collections;

@RestController
@CrossOrigin
@RequestMapping(value = "/index-reader-handler")
@Slf4j
@Tag(name = "Index Data Reader Controller", description = "Read Index Data Using This Controller Api")
public class IndexerDataReaderController {

    @Autowired
    private IndexReaderImpl indexReaderService;

    @PostMapping(value = "/index-query/process", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> processIndexQuery(@RequestBody @Valid String query) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Index search query submitted successfully!")
                            .data(Collections.singletonMap("result", indexReaderService.processQueryUsingQueryParser(query, 10)))
                            .build()
            );
        } catch (IOException exception) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(exception.getMessage())
                            .build()
            );
        }
    }
}
