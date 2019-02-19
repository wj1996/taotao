package com.wj.taotao.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

public class FastDfsClientUtils {

    private TrackerClient trackerClient;
    private TrackerServer trackerServer;
    private StorageServer storageServer;
    private StorageClient1 storageClient;

    public FastDfsClientUtils(String conf) throws Exception{
        if(conf.contains("classpath")){
            conf = conf.replace("classpath:",this.getClass().getResource("/").getPath());
        }

        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer,storageServer);
    }

    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        return storageClient.upload_file1(fileName,extName,metas);
    }

    public String uploadFile(byte[] fileContent,String extName, NameValuePair[] metas) throws Exception {
        return storageClient.upload_file1(fileContent,extName,metas);
    }
}
