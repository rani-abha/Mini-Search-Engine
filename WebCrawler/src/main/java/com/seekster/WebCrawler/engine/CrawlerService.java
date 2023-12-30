package com.seekster.WebCrawler.engine;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CrawlerService {
    public String startCrawling(String url) throws Exception {
        CompletableFuture.runAsync(
                () -> {
                    CrawlController controller;
                    try {
                        controller = getCrawlController();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    controller.addSeed(url);

                    // The factory which creates instances of crawlers.
                    CrawlController.WebCrawlerFactory<Crawler> factory = Crawler::new;

                    // Start the crawl. This is a blocking operation, meaning that your code
                    // will reach the line after this only when crawling is finished.
                    controller.start(factory, 5);
                });
        return url;
    }

    private static CrawlController getCrawlController() throws Exception {
        CrawlConfig config = CrawlConfiguration.getCrawlConfigFromDb();
        // Instantiate the controller for this crawl.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = RobotTxtConfiguration.getRobotstxtConfigFromDb();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        return new CrawlController(config, pageFetcher, robotstxtServer);
    }
}
