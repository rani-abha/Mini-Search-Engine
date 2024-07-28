package com.seekster.WebCrawler.api.dto;

import com.seekster.WebCrawler.constant.SettingType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SettingDto {

    private SettingType type;

    private String key;

    private String value;

    private String label;
}
