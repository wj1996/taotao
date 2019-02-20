package com.wj.taotao.test;

import org.csource.fastdfs.*;

public class FastdfsClientDemo {

    public static void main(String[] args) throws Exception{
        ClientGlobal.init("D:\\wk\\idea\\taotao\\taotao-content\\taotao-content-service\\src\\main\\resources\\fdfs_client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
//        StorageClient storageClient = new StorageClient(trackerServer,storageServer);

        StorageClient1 storageClient = new StorageClient1(trackerServer,storageServer);
        String path = storageClient.upload_file1("D:\\wk\\idea\\taotao\\taotao-content\\taotao-content-service\\src\\test\\java\\com\\wj\\taotao\\test\\123.jpg", "jpg", null);

        System.out.println(path);

        /*String[] result = storageClient.upload_file("D:\\wk\\idea\\taotao\\taotao-content\\taotao-content-service\\src\\test\\java\\com\\wj\\taotao\\test\\123.jpg", "jpg", null);
        for(String str : result){
            System.out.println(str);
        }*/

    }
}
