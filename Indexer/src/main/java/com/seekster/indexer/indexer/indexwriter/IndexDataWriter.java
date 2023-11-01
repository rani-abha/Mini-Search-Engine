package com.seekster.indexer.indexer.indexwriter;

import com.seekster.indexer.rabbitmq.message.ContentMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Slf4j
public class IndexDataWriter {

    private IndexWriter indexWriter;
    private Directory directory;
    private IndexWriterConfig indexWriterConfig;
    private StandardAnalyzer analyzer;
    private String pathForIndexData;
    private long lastActiveOn;

    public IndexDataWriter(String pathForIndexData) throws IOException {
        this.directory = FSDirectory.open(new File(pathForIndexData).toPath());
        this.pathForIndexData = pathForIndexData;
        this.analyzer = new StandardAnalyzer();
        this.indexWriterConfig = new IndexWriterConfig(analyzer);
        this.indexWriter = new IndexWriter(directory, indexWriterConfig);
    }

    public void write(ContentMessage message) {
        // creating fields for document
        List<Field> fields = createTextField(message);

        // creating document with fields
        Document document = createDocument(fields);
        try {
            // creating index Writer to write index data
            indexWriter.addDocument(document);
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            log.error("Error while Creating Index Writer with Document: " + document);
        }
        this.lastActiveOn = System.currentTimeMillis();
    }

    private Document createDocument(List<Field> fields) {
        Document document = new Document();
        for (Field field : fields) {
            document.add(field);
        }
        return document;
    }

    private List<Field> createTextField(ContentMessage message) {
        List<Field> fields = new ArrayList<>();
        fields.add(new TextField("url", message.getUrl(), Field.Store.YES));
        fields.add(new TextField("title", message.getTitle(), Field.Store.YES));
        fields.add(new TextField("content", message.getContent(), Field.Store.YES));
        return fields;
    }

    public long idleSince() {
        return System.currentTimeMillis() - lastActiveOn;
    }

}
