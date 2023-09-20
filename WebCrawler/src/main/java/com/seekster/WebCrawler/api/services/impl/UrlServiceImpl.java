package com.seekster.WebCrawler.api.services.impl;

import com.seekster.WebCrawler.api.mapperconverter.DtoConverter;
import com.seekster.WebCrawler.api.controllers.dto.UrlDto;
import com.seekster.WebCrawler.api.exceptions.GenericException;
import com.seekster.WebCrawler.api.models.Url;
import com.seekster.WebCrawler.api.repositories.UrlRepo;
import com.seekster.WebCrawler.api.services.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private DtoConverter<UrlDto, Url> dtoConverter;

    @Autowired
    private UrlRepo urlRepo;

    @Override
    public List<Url> getUrlList() throws GenericException {
        return null;
    }

    @Override
    public Url createUrl(UrlDto urlDto) throws GenericException {
        return null;
    }

    @Override
    public Url updateUrl(String id, UrlDto urlDto) throws GenericException {
        return null;
    }

    @Override
    public Url deleteUrl(String id) throws GenericException {
        return null;
    }
}
