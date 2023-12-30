package com.seekster.WebCrawler.api.services.impl;

import com.seekster.WebCrawler.api.dto.WebsiteDto;
import com.seekster.WebCrawler.api.exceptions.GenericException;
import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.api.mapperconverter.DtoConverter;
import com.seekster.WebCrawler.api.models.Website;
import com.seekster.WebCrawler.api.repositories.WebsiteRepo;
import com.seekster.WebCrawler.api.services.WebsiteSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class WebsiteSvcImpl implements WebsiteSvc {
    @Autowired
    private DtoConverter<WebsiteDto, Website> dtoConverter;
    @Autowired
    private WebsiteRepo websiteRepo;

    @Override
    public Page<Website> getWebsiteList(int page, int pageSize) throws GenericException {
        log.info("Starting execution of getWebsiteList method");
        long noOfDeviceField = websiteRepo.count();
        if (noOfDeviceField > 0) {
            Pageable pageable = PageRequest.of(page, pageSize);
            log.debug("fetching website information from database");
            return websiteRepo.findAll(pageable);
        }
        log.error("There are no website object found in database");
        throw new GenericException("No website exists in database");
    }

    @Override
    public Website createWebsite(WebsiteDto websiteDto) throws ValidationException, IllegalArgumentException {
        log.info("Started Execution of createWebsite method");
        List<Website> isWebsiteExists = websiteRepo.findAll()
                .stream()
                .filter(website -> website.getSeed().equalsIgnoreCase(websiteDto.getSeed()))
                .toList();
        if (isWebsiteExists.isEmpty()) {
            Website website = dtoConverter.convert(websiteDto, Website.class);
            try {
                log.debug("saving website info in database");
                return websiteRepo.save(website);
            } catch (IllegalArgumentException exception) {
                log.error("Execution --> ", exception);
                throw new IllegalArgumentException(exception.getMessage());
            }
        }
        log.error("Website already exists in database");
        throw new ValidationException("Website already exists in database");
    }

    @Override
    public Website updateWebsite(String id, WebsiteDto websiteDto) throws GenericException, ValidationException, IllegalArgumentException {
        log.info("Started execution of updateWebsite method");
        Optional<Website> website = websiteRepo.findById(id);
        List<Website> isWebsiteExists = websiteRepo.findAll()
                .stream()
                .filter(site -> site.getSeed().equalsIgnoreCase(websiteDto.getSeed()))
                .toList();
        if (!isWebsiteExists.isEmpty()) {
            log.error("Website already exists in database");
            throw new ValidationException("Website already exists in database");
        }
        if (website.isPresent()) {
            website.get().setSeed(websiteDto.getSeed());
            try {
                log.debug("updating existing website with id : " + id);
                return websiteRepo.save(website.get());
            } catch (IllegalArgumentException exception) {
                log.error("Execution --> ", exception);
                throw new IllegalArgumentException(exception.getMessage());
            }
        }
        log.error("Website already exists in database");
        throw new GenericException("Website already exists in database");
    }

    @Override
    public Website deleteWebsite(String id) throws GenericException, IllegalArgumentException {
        log.info("started execution of deleteWebsite method");
        Optional<Website> website = websiteRepo.findById(id);
        if (website.isPresent()) {
            try {
                websiteRepo.delete(website.get());
                return website.get();
            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException("id can't be null");
            }
        }
        throw new GenericException("Website not found with id : " + id);
    }
}
