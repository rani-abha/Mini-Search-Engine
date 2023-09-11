package com.seekster.WebCrawler.services;

import com.seekster.WebCrawler.dto.UrlDto;
import com.seekster.WebCrawler.exceptions.GenericException;
import com.seekster.WebCrawler.models.Url;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UrlService {

    List<Url> getUrlList() throws GenericException;

    Url createUrl(UrlDto urlDto) throws GenericException;

    Url updateUrl(String id, UrlDto urlDto) throws GenericException;

    Url deleteUrl(String id) throws GenericException;

}
