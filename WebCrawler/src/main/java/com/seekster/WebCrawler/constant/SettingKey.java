package com.seekster.WebCrawler.constant;

public class SettingKey {
    public enum CrawlKey {
        crawlStorageFolder, resumableCrawling, dbLockTimeout, maxDepthOfCrawling, maxPagesToFetch, userAgentString, politenessDelay,
        includeHttpsPages, includeBinaryContentInCrawling, processBinaryContentInCrawling, maxConnectionsPerHost, maxTotalConnections, socketTimeout,
        connectionTimeout, maxOutgoingLinksToFollow, maxDownloadSize, followRedirects, onlineTldListUpdate, shutdownOnEmptyQueue, threadMonitoringDelaySeconds,
        threadShutdownDelaySeconds, cleanupDelaySeconds, proxyHost, proxyPort, proxyUsername, proxyPassword, respectNoFollow, respectNoIndex
    }

    public enum RobotTxt {
        enabled, userAgentName, ignoreUADiscrimination, cacheSize
    }
}
