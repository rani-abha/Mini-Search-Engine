package com.seekster.indexer.rabbitmq.message;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentMessage {
    private String url;

    private String title;

    private String content;
}
