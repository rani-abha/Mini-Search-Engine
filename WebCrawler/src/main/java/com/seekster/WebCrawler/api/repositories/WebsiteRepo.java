package com.seekster.WebCrawler.api.repositories;

import com.seekster.WebCrawler.api.models.Website;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepo extends MongoRepository<Website, String> {
}
