package com.seekster.WebCrawler.repositories;

import com.seekster.WebCrawler.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends MongoRepository<Url, String> {
}
