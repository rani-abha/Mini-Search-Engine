package com.seekster.WebCrawler.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UrlDto {

    @Id
    private Long id;

    @NotEmpty
    @Pattern(regexp = "^(https?://[^/]+/?|[^/]+/?)$\n", message = "Invalid url or not root url")
    private String address;
}
