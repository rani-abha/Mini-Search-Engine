package com.seekster.WebCrawler.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {

    @Autowired
    private Environment env;

    public String startCrawling(String url) {
        int numberOfThreads = Integer.parseInt(env.getProperty("crawler.numberOfThreads"));
        int maximumLimit = Integer.parseInt(env.getProperty("crawler.maximumLimit"));
        CrawlerQueue crawlerQueue = new CrawlerQueue();
        // Create a thread pool task executor
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(numberOfThreads);
        executor.setMaxPoolSize(numberOfThreads);
        executor.initialize();
        System.out.println(url);
        crawlerQueue.addListOfSites(url);

        // Initialize crawling with multiple threads
        Crawler.initializeCrawling(numberOfThreads, crawlerQueue, maximumLimit, "ThreadPrefix");

        System.out.println(crawlerQueue.getListOfSites());
        // Shutdown the executor when crawling is finished
        executor.shutdown();
        return url;
    }
}
