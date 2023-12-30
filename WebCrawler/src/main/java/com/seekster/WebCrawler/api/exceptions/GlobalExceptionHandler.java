package com.seekster.WebCrawler.api.exceptions;

import com.seekster.WebCrawler.api.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException (MethodArgumentNotValidException exception){
        Map<String, String> response = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            response.put(fieldName, message);
        }));
        return ResponseEntity.ok(
                Response.builder()
                        .responseTime(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("Input Fields are Not Valid!")
                        .data(Collections.singletonMap("error", response))
                        .build()
        );
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleHttpMessageNotReadableException (HttpMessageNotReadableException exception){
        Map<String, String> response = new HashMap<>();
        String name = "HttpMessageNotReadableException";
        String message = exception.getMessage();
        response.put(name, message);
        return ResponseEntity.ok(
                Response.builder()
                        .responseTime(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("HttpMessage Not Readable!")
                        .data(Collections.singletonMap("error", response))
                        .build()
        );
    }

}
