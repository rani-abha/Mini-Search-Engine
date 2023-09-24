package com.seekster.WebCrawler.engine;

import com.seekster.WebCrawler.rabbitmq.MessageSender;
import com.seekster.WebCrawler.rabbitmq.RabbitMqConstants;
import com.seekster.WebCrawler.rabbitmq.message.ContentMessage;
import com.seekster.WebCrawler.registry.ApplicationContextProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Pattern;

public class HTMLParser {

    private final static Pattern Filters = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g"
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf"
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    private static final MessageSender messageSender = ApplicationContextProvider.getApplicationContext().getBean(MessageSender.class);

    public static int getPR(String domain) {
        String result = "";
        //JenkinsHash jenkinsHash = new JenkinsHash();
        //long hash = jenkinsHash.hash(("info:" + domain).getBytes());
        long hash = JenkinsHash.hash(("info:" + domain).getBytes());
        //Append a 6 in front of the hashing value.
        String url = "http://toolbarqueries.google.com/tbr?client=navclient-auto&hl=en&"
                + "ch=6" + hash + "&ie=UTF-8&oe=UTF-8&features=Rank&q=info:" + domain;
        // System.out.println("Sending request to : " + url);
        try {
            URLConnection conn = new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String input;
            while ((input = br.readLine()) != null) {
                // What does Google return? -> Example : Rank_1:1:9, PR = 9
                // System.out.println(input);
                result = input.substring(input.lastIndexOf(":") + 1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if ("".equals(result)) {
            return 0;
        } else {
            return Integer.valueOf(result);
        }

    }

    public static void getLinks(CrawlerQueue crawledSites, int t, int numberOfLinksToCrawl, String s) throws IOException {
        //  System.out.println("Thread: " + t);
        String url = crawledSites.getNextLink();
        Elements links = null;
        Document dc = null;
        Document doc = null;
        String lnk;
        int pageRank = 0;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(3000).get();
            ContentMessage contentMessage = new ContentMessage();
            contentMessage.setUrl(url);
            contentMessage.setTitle(doc.title());
            contentMessage.setContent(doc.select("p").text());
            putData(contentMessage);
            System.out.println("Thread: " + t + " URL: " + s + " added: " + url);
            crawledSites.addCrawledSites(url);
            crawledSites.incCount();
            // System.out.println("Length: "+crawledSites.getCrawledSites().size());
            //System.out.println("No.Of Links Crawled: " + crawledSites.getCount());
            links = doc.select("a[href]");
        } catch (Exception e) {
            links = null;
        } finally {
            //max queue size at all times is 5x the no. of links required to crawl!
            if (links != null && crawledSites.getQueueLength() < (numberOfLinksToCrawl * 5)) {
                for (Element link : links) {
                    lnk = link.absUrl("href");
                    if (shouldVisit(lnk)) {
                        //adding to queue
                        crawledSites.addListOfSites(lnk);
                    }
                }
            }
            links = null;
        }
    }

    public static void putData(ContentMessage contentMessage) {

//        MessageSender messageSender = new MessageSender();
        messageSender.send(contentMessage, RabbitMqConstants.QUEUE_CRAWLER_CONTENT_SEND);

//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("example.txt"))) {
//            writer.write(contentMessage.getContent());
//            System.out.println("Text document created successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("An error occurred while creating the text document.");
//        }
        System.out.println(contentMessage);
    }

    public static String getDomain(String url) throws MalformedURLException {
        String cleanUrl = url.toLowerCase().trim();
        URL link = new URL(cleanUrl);
        String domain = link.getHost();
        System.out.println(domain);
        return domain;
    }

    public static boolean shouldVisit(String url) {
        String cleanUrl = url.toLowerCase().trim();
        boolean shouldVisit = true;
        if (url.contains("wikipedia")) {
            //if it is a wikipedia link, only allow english pages!
            if (!url.contains("en.wiki")) {
                shouldVisit = false;
            }
        }
        if (url.contains("dmoz - world")) {
            if (!url.contains("english")) {
                shouldVisit = false;
            }
        }
        if (Filters.matcher(cleanUrl).matches()) {
            //if it matches any of the pdf,gif jpg etc etc.. return false
            shouldVisit = false;
        }
        return shouldVisit;

    }
}
