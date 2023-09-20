package com.seekster.WebCrawler.rabbitmq.message;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContentMessage {

    private String url;

    private String title;

    private String content;
}
