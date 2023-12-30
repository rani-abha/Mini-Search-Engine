package com.seekster.WebCrawler.api.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "website")
public class Website {

    @Id
    private String id;

    private String seed;

    private boolean isCrawl;
}
