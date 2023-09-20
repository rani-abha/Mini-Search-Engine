package com.seekster.WebCrawler.crawler.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface CrawlerEngineService {
    void crawl(String url, int maxDepth);
}
