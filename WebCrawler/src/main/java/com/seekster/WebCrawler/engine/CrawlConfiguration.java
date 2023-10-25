package com.seekster.WebCrawler.engine;

import com.seekster.WebCrawler.api.dto.SettingDto;
import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.api.services.impl.SettingSvcImpl;
import com.seekster.WebCrawler.constant.SettingType;
import com.seekster.WebCrawler.registry.ApplicationContextProvider;
import com.seekster.WebCrawler.singleton.CrawlSettingSingleton;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;

public class CrawlConfiguration {
    private static final SettingSvcImpl settingSvc = ApplicationContextProvider.getApplicationContext().getBean(SettingSvcImpl.class);

    public static CrawlConfig getDefault() {
        return new CrawlConfig();
    }

    public static CrawlConfig getCrawlConfigFromDb() {
        CrawlConfig crawlConfig = getDefault();
        CrawlSettingSingleton crawlSettingSingleton = CrawlSettingSingleton.getInstance();
        crawlConfig.setCrawlStorageFolder("src/main/resources" + crawlSettingSingleton.getCrawlStorageFolder());
        crawlConfig.setResumableCrawling(crawlSettingSingleton.isResumableCrawling());
        crawlConfig.setDbLockTimeout(crawlSettingSingleton.getDbLockTimeout());
        crawlConfig.setMaxDepthOfCrawling(crawlSettingSingleton.getMaxDepthOfCrawling());
        crawlConfig.setMaxPagesToFetch(crawlSettingSingleton.getMaxPagesToFetch());
        crawlConfig.setUserAgentString(crawlSettingSingleton.getUserAgentString());
        crawlConfig.setPolitenessDelay(crawlSettingSingleton.getPolitenessDelay());
        crawlConfig.setIncludeHttpsPages(crawlSettingSingleton.isIncludeHttpsPages());
        crawlConfig.setIncludeBinaryContentInCrawling(crawlSettingSingleton.isIncludeBinaryContentInCrawling());
        crawlConfig.setProcessBinaryContentInCrawling(crawlSettingSingleton.isProcessBinaryContentInCrawling());
        crawlConfig.setMaxConnectionsPerHost(crawlSettingSingleton.getMaxConnectionsPerHost());
        crawlConfig.setMaxTotalConnections(crawlSettingSingleton.getMaxTotalConnections());
        crawlConfig.setSocketTimeout(crawlSettingSingleton.getSocketTimeout());
        crawlConfig.setConnectionTimeout(crawlSettingSingleton.getConnectionTimeout());
        crawlConfig.setMaxOutgoingLinksToFollow(crawlSettingSingleton.getMaxOutgoingLinksToFollow());
        crawlConfig.setMaxDownloadSize(crawlSettingSingleton.getMaxDownloadSize());
        crawlConfig.setFollowRedirects(crawlSettingSingleton.isFollowRedirects());
        crawlConfig.setOnlineTldListUpdate(crawlSettingSingleton.isOnlineTldListUpdate());
        crawlConfig.setShutdownOnEmptyQueue(crawlSettingSingleton.isShutdownOnEmptyQueue());
        crawlConfig.setThreadMonitoringDelaySeconds(crawlSettingSingleton.getThreadMonitoringDelaySeconds());
        crawlConfig.setThreadShutdownDelaySeconds(crawlSettingSingleton.getThreadMonitoringDelaySeconds());
        crawlConfig.setCleanupDelaySeconds(crawlSettingSingleton.getCleanupDelaySeconds());
        crawlConfig.setProxyHost(crawlSettingSingleton.getProxyHost());
        crawlConfig.setProxyPort(crawlSettingSingleton.getProxyPort());
        crawlConfig.setProxyUsername(crawlSettingSingleton.getProxyUsername());
        crawlConfig.setProxyPassword(crawlSettingSingleton.getProxyPassword());
        crawlConfig.setRespectNoFollow(crawlSettingSingleton.isRespectNoFollow());
        crawlConfig.setRespectNoIndex(crawlSettingSingleton.isRespectNoIndex());
        return crawlConfig;
    }

    public static void storeDefaultCrawlConfig() throws ValidationException {
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "crawlStorageFolder", "/", "Crawl storage folder"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "resumableCrawling", "false", "Resume previous crawl"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "dbLockTimeout", "500", "Lock timeout for sleepy_cat Db"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "maxDepthOfCrawling", "-1", "Maximum depth of crawling"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "maxPagesToFetch", "-1", "Maximum number of pages"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "userAgentString", "seekster", "User-Agent"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "politenessDelay", "200", "Politeness delay"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "includeHttpsPages", "true", "Include https pages?"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "includeBinaryContentInCrawling", "false", "fetch binary content like image, audio"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "processBinaryContentInCrawling", "false", "process binary content"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "maxConnectionsPerHost", "100", "Maximum Connections per host"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "maxTotalConnections", "100", "Maximum total Connections"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "socketTimeout", "20000", "socket timeout in milliseconds"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "connectionTimeout", "30000", "connection timeout in milliseconds"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "maxOutgoingLinksToFollow", "5000", "Max number of outgoing links which are processed from a page"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "maxDownloadSize", "1048576", "Max allowed size of a page"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "followRedirects", "true", "redirects"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "onlineTldListUpdate", "false", "TLD list"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "shutdownOnEmptyQueue", "true", "crawler stop running when the queue is empty?"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "threadMonitoringDelaySeconds", "10", "Thread monitor delay in second"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "threadShutdownDelaySeconds", "10", "Thread shutdown delay in second"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "cleanupDelaySeconds", "10", "cleanup delay in second"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "proxyHost", null, "Proxy host"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "proxyPort", "80", "Proxy Port"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "proxyUsername", null, "Proxy username"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "proxyPassword", null, "Proxy password"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "respectNoFollow", "true", "honor \"noFollow\" flag"));
        settingSvc.createSetting(new SettingDto(SettingType.CRAWL, "respectNoIndex", "true", "honor \"noIndex\" flag"));
    }
}
