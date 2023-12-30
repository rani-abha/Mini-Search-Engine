package com.seekster.WebCrawler.api.services.impl;

import com.seekster.WebCrawler.api.dto.SettingDto;
import com.seekster.WebCrawler.api.exceptions.GenericException;
import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.api.mapperconverter.DtoConverter;
import com.seekster.WebCrawler.api.models.Setting;
import com.seekster.WebCrawler.api.models.Website;
import com.seekster.WebCrawler.api.repositories.SettingRepo;
import com.seekster.WebCrawler.api.services.SettingSvc;
import com.seekster.WebCrawler.constant.SettingType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class SettingSvcImpl implements SettingSvc {
    @Autowired
    private SettingRepo settingRepo;
    @Autowired
    private DtoConverter<SettingDto, Setting> converter;
    @Override
    public List<Setting> getSettingList(SettingType settingType) throws GenericException {
        List<Setting> settings = settingRepo.findByType(settingType);
        if (settings.isEmpty()) {
            throw new GenericException("there is no setting exist in database with type " + settings);
        }
        return settings;
    }

    @Override
    public Setting createSetting(SettingDto settingDto) throws IllegalArgumentException, ValidationException {
        log.info("started execution of createSetting method");

        List<Setting> settingList = settingRepo.findAll()
                .stream()
                .filter(setting -> setting.getKey().equalsIgnoreCase(settingDto.getKey())  && setting.getType() == settingDto.getType())
                .toList();

        Setting setting;
        if (settingList.isEmpty()) {
            setting = converter.convert(settingDto, Setting.class);
            try {
                settingRepo.save(setting);
            } catch (IllegalArgumentException exception) {
                log.error(exception.getMessage());
            }
            log.info("Setting added Successfully");
        } else {
            throw new ValidationException("Setting object already exists, try a different key");
        }
        return setting;
    }

    @Override
    public Setting updateSetting(String id, SettingDto settingDto) throws GenericException, ValidationException, IllegalArgumentException {
        log.info("Started execution of updateSetting method");

        log.debug("Checking if Setting Field: {} with id: [{}] is exist in database", settingDto, id);
        Optional<Setting> settingExist = settingRepo.findById(id);

        List<Setting> settingList = settingRepo.findAll()
                .stream()
                .filter(setting -> setting.getKey().equalsIgnoreCase(settingDto.getKey()) &
                        setting.getValue().equals(settingDto.getValue()) &
                        setting.getType().equals(settingDto.getType()) &
                        setting.getLabel().equalsIgnoreCase(settingDto.getLabel()))
                .toList();

        List<Setting> sameGlobalSetting = settingRepo.findAll()
                .stream()
                .filter(setting -> setting.getKey().equals(settingDto.getKey()) & setting.getId().equals(id))
                .toList();

        if (settingExist.isEmpty()) {
            log.error("Setting object with id: {} is not exist in database. Please check.", id);
            throw new GenericException("Setting not found");
        } else if (!settingList.isEmpty()) {
            log.error("Setting object is already exist. Do some changes either in Key, value, label");
            throw new ValidationException("Setting object is already exist. Do some changes either in Key, value, label");
        } else if (!sameGlobalSetting.isEmpty()) {
            log.error("Setting is already exist. Enter some other key");
            throw new ValidationException("Setting is already exist. Enter some other key");
        } else {
            log.info("Global Setting of Id: {} is exist, Updating server...", id);
            settingExist.get().setId(id);
            settingExist.get().setKey(settingDto.getKey());
            settingExist.get().setType(settingDto.getType());
            settingExist.get().setValue(settingDto.getValue());
            settingExist.get().setLabel(settingDto.getLabel());
            try {
                settingRepo.save(settingExist.get());
            } catch (IllegalArgumentException exception) {
                log.error(exception.getMessage());
                throw new IllegalArgumentException(exception.getMessage());
            }
            log.info("Setting updated successfully with server object: {}", settingExist);
        }
        return settingExist.get();
    }

    @Override
    public Setting deleteSetting(String id) throws GenericException, IllegalArgumentException {
        log.info("started execution of deleteWebsite method");
        Optional<Setting> setting = settingRepo.findById(id);
        if (setting.isPresent()) {
            try {
                settingRepo.delete(setting.get());
                return setting.get();
            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException(exception.getMessage());
            }
        }
        throw new GenericException("Setting not found with id : " + id);
    }

    @Override
    public Map<String, String> getListOfSettingKeyValues(SettingType type) throws GenericException {
        log.info("Started execution of getListOfSettingKeyValues method");

        Map<String, String> settingMap = new HashMap<>();
        List<Setting> settings = settingRepo.findByType(type);

        if (settings.isEmpty()) {
            log.error("There are not setting object found to get key & value pair. Please Check!");
            throw new GenericException("Setting not found while getting key & value pair");
        }
        {
            log.info("Preparing map of setting keys and value...");
            for (Setting setting: settings) {
                settingMap.put(setting.getKey(), setting.getValue());
            }
            log.info("Map of setting is: {}", settingMap);
        }
        return settingMap;
    }
}
