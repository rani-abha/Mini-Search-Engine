package com.seekster.WebCrawler.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@ToString
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private LocalDateTime responseTime;
    private HttpStatus status;
    private int statusCode;
    private String message;
    private Map<?, ?> data;
}
