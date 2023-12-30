package com.seekster.WebCrawler.api.repositories;

import com.seekster.WebCrawler.api.models.Setting;
import com.seekster.WebCrawler.constant.SettingType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettingRepo extends MongoRepository<Setting, String> {
    List<Setting> findByType(SettingType type);
}
