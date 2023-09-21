package com.seekster.WebCrawler.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WebsiteDto {
    @NotEmpty
//    @Pattern(regexp = "^(https?://[^/]+/?|[^/]+/?)$\n", message = "Invalid url or not root url")
    private String seed;
    @NotEmpty
    private String sitemap;
}
