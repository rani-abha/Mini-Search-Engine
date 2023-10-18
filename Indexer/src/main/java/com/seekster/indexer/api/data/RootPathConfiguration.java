package com.seekster.indexer.api.data;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("rootPathConfiguration")
@Data
public class RootPathConfiguration {
    @Value("${root.path}")
    private String rootPath;
}
