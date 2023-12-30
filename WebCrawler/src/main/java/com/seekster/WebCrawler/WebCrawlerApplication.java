package com.seekster.WebCrawler;

import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.engine.CrawlConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class WebCrawlerApplication {

	public static void main(String[] args) throws IllegalAccessException {
		SpringApplication.run(WebCrawlerApplication.class, args);
	}
}
