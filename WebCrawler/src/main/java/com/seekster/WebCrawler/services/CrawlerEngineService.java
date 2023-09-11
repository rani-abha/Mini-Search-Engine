package com.seekster.WebCrawler.services;

import org.springframework.stereotype.Service;

@Service
public interface CrawlerEngineService {
    void crawl(String url);
}
