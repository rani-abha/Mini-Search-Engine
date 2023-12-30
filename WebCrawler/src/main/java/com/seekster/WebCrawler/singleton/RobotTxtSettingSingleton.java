package com.seekster.WebCrawler.singleton;

import com.seekster.WebCrawler.api.exceptions.GenericException;
import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.api.models.Setting;
import com.seekster.WebCrawler.api.services.impl.SettingSvcImpl;
import com.seekster.WebCrawler.constant.SettingType;
import com.seekster.WebCrawler.engine.CrawlConfiguration;
import com.seekster.WebCrawler.engine.RobotTxtConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
@Component
@Slf4j
public class RobotTxtSettingSingleton {

    @Autowired
    private SettingSvcImpl settingSvc;
    private static final RobotTxtSettingSingleton INSTANCE = new RobotTxtSettingSingleton();

    private boolean enabled;
    private String userAgentName;
    private boolean ignoreUADiscrimination;
    private int cacheSize;

    private RobotTxtSettingSingleton() {
    }

    @PostConstruct
    private void setFromSettings() throws GenericException, IllegalAccessException {
        try {
            Field[] fields = INSTANCE.getClass().getDeclaredFields();
            Map<String, String> settingMap = settingSvc.getListOfSettingKeyValues(SettingType.ROBOT_TXT);

            for (Field field : fields) {
                field.setAccessible(true);
                if (settingMap.containsKey(field.getName())) {
                    String value = settingMap.get(field.getName());
                    if (field.getType().equals(int.class)) {
                        field.setInt(INSTANCE, Integer.parseInt(value));
                    } else if (field.getType().equals(boolean.class)) {
                        field.setBoolean(INSTANCE, Boolean.parseBoolean(value));
                    } else {
                        field.set(INSTANCE, value);
                    }
                }
            }

            log.info("Printing settings parameters: {}", INSTANCE);
        } catch (GenericException genericException) {
            log.info("No RobotTxt setting found while calling getListOfSettingKeyValues method");
            try {
                RobotTxtConfiguration.storeDefaultRobotTxtConfig();
                setFromSettings();
            } catch (ValidationException validationException) {
                log.error(validationException.getMessage());
                throw new GenericException(validationException.getMessage());
            }
        } catch (IllegalAccessException illegalAccessException) {
            log.error("Exception occurred while setting values from setting object.");
            throw new IllegalAccessException("RobotTxt Setting IllegalAccessException");
        }
    }

    public void setUpdatedValuesFromSetting(Setting setting) throws ValidationException, IllegalAccessException {
        try {

            Field field = INSTANCE.getClass().getField(setting.getKey());
            String value = setting.getValue();

            if (field.getType().equals(int.class)) {
                field.setInt(INSTANCE, Integer.parseInt(value));
            } else if (field.getType().equals(boolean.class)) {
                field.setBoolean(INSTANCE, Boolean.parseBoolean(value));
            } else {
                field.set(INSTANCE, value);
            }

            log.info("Printing updated settings parameters: {}", INSTANCE);
        } catch (IllegalAccessException illegalAccessException) {
            log.error("Exception occurred while setting values from the setting object.");
            throw new IllegalAccessException("Setting IllegalAccessException");
        } catch (NoSuchFieldException e) {
            log.error("no such field found : " + setting.getKey());
            throw new ValidationException("no such field found : " + setting.getKey());
        }
    }

    public static RobotTxtSettingSingleton getInstance() {
        return INSTANCE;
    }
}
