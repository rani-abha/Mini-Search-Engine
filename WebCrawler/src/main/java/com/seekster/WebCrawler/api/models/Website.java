package com.seekster.WebCrawler.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Document(collection = "website")
public class Website {

    @Id
    private String id;

    private String seed;

    private String sitemap;

    private boolean isCrawl;
}
