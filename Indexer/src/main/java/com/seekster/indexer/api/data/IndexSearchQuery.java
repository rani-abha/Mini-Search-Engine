package com.seekster.indexer.api.data;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndexSearchQuery {

    private String type;
    private Object queryParams;
    private String index;
    private Data queryDate;
}
