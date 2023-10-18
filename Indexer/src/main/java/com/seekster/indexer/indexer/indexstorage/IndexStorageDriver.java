//package com.seekster.indexer.indexer.indexstorage;
//
//import com.seekster.indexer.rabbitmq.message.ContentMessage;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class IndexStorageDriver {
//
//    private String storageDir;
//    private int indexFolderPointer = 0;
//    private List<String> indexDirectoryNames = new ArrayList<>();
////    private ConcurrentHashMap<String, IndexDataWriter> activeIndexWriter = new ConcurrentHashMap<>();
//    private int noOfFolderCreatedForThreadsInThreadPool;
//
//    public IndexStorageDriver(String storageDir, int corePoolSize) {
//        this.storageDir = storageDir;
//        this.noOfFolderCreatedForThreadsInThreadPool = corePoolSize;
//        for (int i = 0; i < noOfFolderCreatedForThreadsInThreadPool; i++) {
//            indexDirectoryNames.add("index" + "-" + i);
//        }
//    }
//
//    public void save(ContentMessage packetData) throws IOException {
////        List<PacketAttribute> packet = packetData.getPacketAttributes();
////        Date packetDate = getPacketdate(packet);
//
//        String contentMessages = packetData.getContent();
//        String contentMessagesTitle = packetData.getTitle();
//
//
//        String directoryPath = preparePathToStoreData(packetData);
//        File directory = new File(directoryPath);
//        Files.createDirectories(directory.toPath());
//
//        IndexDataWriter indexContentWriter;
//        synchronized (this) {
//            if (indexFolderPointer >= noOfFolderCreatedForThreadsInThreadPool) {
//                this.indexFolderPointer = 0;
//            }
//            directoryPath = directoryPath + File.separator + indexDirectoryNames.get(this.indexFolderPointer);
//            if (!activeIndexWriter.containsKey(directoryPath)) {
//                indexContentWriter = new IndexDataWriter(directoryPath);
//                activeIndexWriter.put(directoryPath, indexContentWriter);
//            } else {
//                indexContentWriter = activeIndexWriter.get(directoryPath);
//            }
//            indexFolderPointer += 1;
//        }
//        indexContentWriter.write(packetDate, packet);
//    }
//
//    public String preparePathToStoreData(Date packetDate, String deviceDriverId) {
//        String path = storageDir + File.separator + deviceDriverId;
//        String directoryPath = PathUtils.getPathFromHour(packetDate, path);
//        return directoryPath;
//    }
//}
