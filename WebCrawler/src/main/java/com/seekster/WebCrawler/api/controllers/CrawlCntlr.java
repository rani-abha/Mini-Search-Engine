package com.seekster.WebCrawler.api.controllers;

import com.seekster.WebCrawler.api.response.Response;
import com.seekster.WebCrawler.engine.CrawlerService;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/crawl")
@Tag(name = "Crawler Controller", description = "Api related to Crawler")
public class CrawlCntlr {
    @Autowired
    private CrawlerService crawlerService;

    @PostMapping(value = "/website", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "website",
            summary = "To start crawling of specific website, Call this API",
            description = "startCrawling method is HTTP POST mapping to start crawling website."
    )
    public ResponseEntity<Response> startCrawling(@RequestBody @Valid String url) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("url", crawlerService.startCrawling(url));
            map.put("status", "started");
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Website Added successfully!")
                            .data(map)
                            .build()
            );
        } catch (Exception exception) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(exception.getMessage())
                            .build()
            );
        }
    }
}
