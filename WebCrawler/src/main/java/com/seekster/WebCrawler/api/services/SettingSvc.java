package com.seekster.WebCrawler.api.services;

import com.seekster.WebCrawler.api.dto.SettingDto;
import com.seekster.WebCrawler.api.exceptions.GenericException;
import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.api.models.Setting;
import com.seekster.WebCrawler.constant.SettingType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SettingSvc {
    List<Setting> getSettingList(SettingType settingType) throws GenericException;

    Setting createSetting(SettingDto settingDto) throws GenericException, ValidationException;

    Setting updateSetting(String id, SettingDto settingDto) throws GenericException, ValidationException, IllegalArgumentException;

    Setting deleteSetting(String id) throws GenericException, IllegalArgumentException;

    Map<String, String> getListOfSettingKeyValues(SettingType type) throws GenericException;
}
