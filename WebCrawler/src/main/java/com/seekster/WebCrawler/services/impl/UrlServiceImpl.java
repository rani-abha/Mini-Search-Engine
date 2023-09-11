package com.seekster.WebCrawler.services.impl;

import com.seekster.WebCrawler.config.mapperconverter.DtoConverter;
import com.seekster.WebCrawler.dto.UrlDto;
import com.seekster.WebCrawler.exceptions.GenericException;
import com.seekster.WebCrawler.models.Url;
import com.seekster.WebCrawler.repositories.UrlRepo;
import com.seekster.WebCrawler.services.UrlService;
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
