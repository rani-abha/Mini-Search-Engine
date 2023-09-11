package com.seekster.WebCrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCrawlerApplication.class, args);

//		WebCrawlerService webCrawlerService = new WebCrawlerService();
//		webCrawlerService.crawl("https://docs.github.com/en/copilot/overview-of-github-copilot/about-github-copilot-for-individuals#about-github-copilot");
	}

}
