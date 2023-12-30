package com.seekster.indexer.api.data;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceivedIndexQuery {
    private Long id;
    private String status;
    private String queryRequestId;
    private int priority;
//  The type or category of the search query.
    private String queryType;
    private IndexSearchQuery search;
    private LocalDateTime submittedDate;
    private LocalDateTime lastModifiedDate;
}
