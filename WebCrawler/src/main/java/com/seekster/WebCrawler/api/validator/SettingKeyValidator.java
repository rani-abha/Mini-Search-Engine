package com.seekster.WebCrawler.api.validator;

import com.seekster.WebCrawler.api.dto.SettingDto;
import com.seekster.WebCrawler.api.models.Setting;
import com.seekster.WebCrawler.constant.SettingKey;
import com.seekster.WebCrawler.constant.SettingType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

import java.util.Arrays;

public class SettingKeyValidator implements ConstraintValidator<ValidateSettingKey, Object> {

    @Override
    public boolean isValid(Object setting, ConstraintValidatorContext context) {
        if (setting instanceof Setting || setting instanceof SettingDto) {
            String key;
            SettingType type;

            if (setting instanceof Setting settingObj) {
                key = settingObj.getKey();
                type = settingObj.getType();
            } else {
                SettingDto settingDto = (SettingDto) setting;
                key = settingDto.getKey();
                type = settingDto.getType();
            }

            if (key == null || type == null) {
                return false;
            }

            return switch (type) {
                case CRAWL -> isValidCrawlKey(key);
                case ROBOT_TXT -> isValidRobotTxt(key);
            };
        }
        throw new ValidationException("Invalid Object type!!, it should be Setting or SettingDto object");
    }

    private boolean isValidCrawlKey(String key) {
        return Arrays.stream(SettingKey.CrawlKey.values())
                .anyMatch(enumKey -> enumKey.name().equals(key));
    }

    private boolean isValidRobotTxt(String key) {
        return Arrays.stream(SettingKey.RobotTxt.values())
                .anyMatch(enumKey -> enumKey.name().equals(key));
    }
}
