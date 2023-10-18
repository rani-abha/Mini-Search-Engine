package com.seekster.indexer.indexer.indexstorage;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexUtils {

    private IndexUtils() {
        throw new IllegalStateException("Index Utility class");
    }

    public static Directory openDirectory(String indexPath) throws IOException {
        Path path = Paths.get(indexPath);

        // Check if the provided path exists and is accessible
        if (!Files.exists(path) || !Files.isDirectory(path) || !Files.isReadable(path)) {
            throw new IllegalArgumentException("Invalid index directory path: " + indexPath);
        }

        // Create the index directory
        return FSDirectory.open(path);
    }
}
