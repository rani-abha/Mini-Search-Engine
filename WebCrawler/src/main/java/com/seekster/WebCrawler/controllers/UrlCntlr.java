package com.seekster.WebCrawler.controllers;

import com.seekster.WebCrawler.exceptions.GenericException;
import com.seekster.WebCrawler.response.Response;
import com.seekster.WebCrawler.services.impl.UrlServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/urls")
@Tag(name = "Url Controller", description = "Api related to fetch, create, update and delete urls")
public class UrlCntlr {

    @Autowired
    private UrlServiceImpl urlService;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "getAll",
            summary = "To get List of Urls, Call this API",
            description = "getUrlList method is HTTP GET mapping so to get data from database."
    )
    public ResponseEntity<Response> getUrlList() {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Url list fetched successfully!")
                            .data(Collections.singletonMap("url", urlService.getUrlList()))
                            .build()
            );
        } catch (GenericException resourceNotFound) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(resourceNotFound.getMessage())
                            .build()
            );
        }
    }
}
