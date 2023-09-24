package com.seekster.WebCrawler.api.controllers;

import com.seekster.WebCrawler.api.dto.WebsiteDto;
import com.seekster.WebCrawler.api.exceptions.GenericException;
import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.api.response.Response;
import com.seekster.WebCrawler.api.services.impl.WebsiteSvcImpl;
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
import java.util.Collections;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/urls")
@Tag(name = "Website Controller", description = "Api related to fetch, create, update and delete urls")
public class WebsiteCntlr {

    @Autowired
    private WebsiteSvcImpl websiteSvc;
    @Autowired
    private CrawlerService crawlerService;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "getAll",
            summary = "To get List of Urls, Call this API",
            description = "getUrlList method is HTTP GET mapping so to get data from database."
    )
    public ResponseEntity<Response> getUrlList(@RequestParam @Valid int page, @RequestParam @Valid int pageSize) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Url list fetched successfully!")
                            .data(Collections.singletonMap("url", websiteSvc.getWebsiteList(page, pageSize)))
                            .build()
            );
        } catch (GenericException exception) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(exception.getMessage())
                            .build()
            );
        }
    }

    @PostMapping(value = "/add-website", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "add-website",
            summary = "To add website for crawling, Call this API",
            description = "addWebsite method is HTTP POST mapping so to add data to database."
    )
    public ResponseEntity<Response> addWebsite(@RequestBody @Valid WebsiteDto websiteDto) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Website Added successfully!")
                            .data(Collections.singletonMap("website", websiteSvc.createWebsite(websiteDto)))
                            .build()
            );
        } catch (ValidationException | IllegalArgumentException exception) {
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
    @PutMapping(value = "/update-website/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "update-website",
            summary = "To update your existing website info, Call this API",
            description = "updateWebsite method is HTTP PUT mapping so to modify data in database."
    )
    public ResponseEntity<Response> updateWebsite(@RequestBody @Valid WebsiteDto websiteDto, @PathVariable("id") @Valid String id)  {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Website updated successfully!")
                            .data(Collections.singletonMap("website", websiteSvc.updateWebsite(id, websiteDto)))
                            .build()
            );
        } catch (ValidationException | GenericException | IllegalArgumentException exception) {
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

    @DeleteMapping(value = "/delete-website/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "delete-website",
            summary = "To remove your website for crawling, Call this API",
            description = "deleteWebsite method is HTTP DELETE mapping so to remove data from database."
    )
    public ResponseEntity<Response> deleteWebsite(@PathVariable("id") @Valid String id) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Website removed successfully!")
                            .data(Collections.singletonMap("website", websiteSvc.deleteWebsite(id)))
                            .build()
            );
        } catch (GenericException | IllegalArgumentException exception) {
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
