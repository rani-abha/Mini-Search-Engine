package com.seekster.indexer.controllers;

import com.seekster.indexer.rabbitmq.message.ContentMessage;
import com.seekster.indexer.response.Response;
import com.seekster.indexer.services.IndexingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/index")
@CrossOrigin
@Slf4j
public class SeeksterIndexingController {

    @Autowired
    IndexingService indexingService;

    @PostMapping(value = "/trigger", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> triggerIndexing(@RequestBody ContentMessage contentMessage) {
        try {
            indexingService.handleContentMessage(contentMessage);

            // Create a successful response
            Response successResponse = Response.builder()
                    .responseTime(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .message("Indexing triggered successfully!")
                    .method("SeeksterIndexingController.triggerIndexing")
                    .executionMessage("Implemented business logic of service class method")
                    .data(Collections.singletonMap("index", "Indexing completed successfully"))
                    .build();

            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            // Create an error response
            Response errorResponse = Response.builder()
                    .responseTime(LocalDateTime.now())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Error while triggering indexing: " + e.getMessage())
                    .method("SeeksterIndexingController.triggerIndexing")
                    .executionMessage("Error in business logic of service class method")
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}


//    @PostMapping(value= "/trigger", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> triggerIndexing(@RequestBody ContentMessage contentMessage) {
//        try {
//            return ResponseEntity.ok(
//                    Response.builder()
//                            .responseTime(LocalDateTime.now())
//                            .status(HttpStatus.OK)
//                            .statusCode(HttpStatus.OK.value())
//                            .message("Indexing triggered successfully!")
//                            .method("SeeksterIndexingController.addToIndex")
//                            .executionMessage("Implemented business logic of service class method")
//                            .data(Collections.singletonMap("index", indexingService.handleContentMessage(contentMessage)))
//                            .build()
//            );
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(
//                    Response.builder()
//                            .responseTime(LocalDateTime.now())
//                            .status(HttpStatus.NOT_FOUND)
//                            .statusCode(HttpStatus.NOT_FOUND.value())
//                            .message("Tacacs Access Level Group which you trying to add is already exist")
//                            .method("AccessLevelGroupController.createAccessLevelGroup")
//                            .executionMessage("Implemented business logic of service class method")
//                            .build()
//            );        }
////        try {
////            indexingService.handleContentMessage(contentMessage);
////            return new ResponseEntity<>("Indexing triggered successfully.", HttpStatus.OK);
////        } catch (Exception e) {
////            return new ResponseEntity<>("Error while triggering indexing: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
////        }
//    }
//}
