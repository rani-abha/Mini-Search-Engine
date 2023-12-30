package com.seekster.WebCrawler.api.services;

import com.seekster.WebCrawler.api.dto.WebsiteDto;
import com.seekster.WebCrawler.api.exceptions.GenericException;
import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.api.models.Website;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WebsiteSvc {

    Page<Website> getWebsiteList(int page, int pageSize) throws GenericException;

    Website createWebsite(WebsiteDto websiteDto) throws ValidationException;

    Website updateWebsite(String id, WebsiteDto websiteDto) throws GenericException, ValidationException, IllegalArgumentException;

    Website deleteWebsite(String id) throws GenericException, IllegalArgumentException;

}
