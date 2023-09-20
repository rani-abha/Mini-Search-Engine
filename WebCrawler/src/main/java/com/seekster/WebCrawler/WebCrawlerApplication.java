package com.seekster.WebCrawler;

import com.seekster.WebCrawler.crawler.interfaces.impl.CrawlerEngineServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCrawlerApplication.class, args);

		CrawlerEngineServiceImpl crawlerEngineService = new CrawlerEngineServiceImpl();
		crawlerEngineService.crawl("https://www.airtel.in/speedtest/", 0);
	}

}
