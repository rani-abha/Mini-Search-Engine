package com.seekster.WebCrawler.engine;

import com.seekster.WebCrawler.api.dto.SettingDto;
import com.seekster.WebCrawler.api.exceptions.GenericException;
import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.api.models.Setting;
import com.seekster.WebCrawler.api.services.impl.SettingSvcImpl;
import com.seekster.WebCrawler.constant.SettingType;
import com.seekster.WebCrawler.registry.ApplicationContextProvider;
import com.seekster.WebCrawler.singleton.RobotTxtSettingSingleton;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;

import java.util.List;

public class RobotTxtConfiguration {
    private static final SettingSvcImpl settingSvc = ApplicationContextProvider.getApplicationContext().getBean(SettingSvcImpl.class);

    public static RobotstxtConfig getDefault() {
        return new RobotstxtConfig();
    }

    public static RobotstxtConfig getRobotstxtConfigFromDb() {
        RobotstxtConfig robotstxtConfig = getDefault();
        RobotTxtSettingSingleton robotTxtSettingSingleton = RobotTxtSettingSingleton.getInstance();
        robotstxtConfig.setEnabled(robotTxtSettingSingleton.isEnabled());
        robotstxtConfig.setUserAgentName(robotTxtSettingSingleton.getUserAgentName());
        robotstxtConfig.setIgnoreUADiscrimination(robotTxtSettingSingleton.isIgnoreUADiscrimination());
        robotstxtConfig.setCacheSize(robotTxtSettingSingleton.getCacheSize());
        return robotstxtConfig;
    }

    public static void storeDefaultRobotTxtConfig() throws ValidationException {
        settingSvc.createSetting(new SettingDto(SettingType.ROBOT_TXT, "enabled", "true", "obey Robots.txt protocol?"));
        settingSvc.createSetting(new SettingDto(SettingType.ROBOT_TXT, "userAgentName", "seekster", "user-agent name"));
        settingSvc.createSetting(new SettingDto(SettingType.ROBOT_TXT, "ignoreUADiscrimination", "false", "user-agent discrimination"));
        settingSvc.createSetting(new SettingDto(SettingType.ROBOT_TXT, "cacheSize", "500", "maximum number of hosts for which their robots.txt"));

    }
}
