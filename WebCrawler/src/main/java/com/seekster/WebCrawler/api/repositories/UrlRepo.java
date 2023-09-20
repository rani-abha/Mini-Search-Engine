package com.seekster.WebCrawler.api.repositories;

import com.seekster.WebCrawler.api.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends MongoRepository<Url, String> {
}
