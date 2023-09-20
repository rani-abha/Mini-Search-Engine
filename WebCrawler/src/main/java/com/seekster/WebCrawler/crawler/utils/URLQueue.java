package com.seekster.WebCrawler.crawler.utils;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class URLQueue {
    private final Queue<URI> queue = new ConcurrentLinkedQueue<>();

    public void enqueue(URI uri) {
        queue.add(uri);
    }

    public URI dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
