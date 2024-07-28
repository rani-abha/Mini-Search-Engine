package com.seekster.WebCrawler.api.models;

import com.seekster.WebCrawler.constant.SettingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "settings")
public class Setting {

    @Id
    private String id;

    private SettingType type;

    private String key;

    private String value;

    private String label;
}