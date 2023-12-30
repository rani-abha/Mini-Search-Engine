package com.seekster.WebCrawler.engine;

import com.seekster.WebCrawler.api.models.Website;
import com.seekster.WebCrawler.api.repositories.WebsiteRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ScheduleCrawling {
    @Autowired
    private WebsiteRepo websiteRepo;
    @Autowired
    private CrawlerService crawlerService;

    @Scheduled(fixedRate = 1800000) // 30 minutes in milliseconds
    public void myScheduledMethod() throws Exception {
        List<Website> websiteList = fetchWebsite();
        if (!websiteList.isEmpty()) {
            crawlerService.startCrawling(websiteList.get(0).getSeed());
            websiteList.get(0).setCrawl(true);
            websiteRepo.save(websiteList.get(0));
        }
    }

    public List<Website> fetchWebsite() {
        log.info("Started fetching website which is not crawled");
        return websiteRepo.findAll()
                .stream()
                .filter(website -> !website.isCrawl())
                .toList();
    }
}
