package com.jugu.www.pcbonlinev2.service;

import java.io.File;
import java.io.InputStream;

/**
 * 文件上传服务接口
 */
public interface FileService {
    /**
     * 文件上传
     * @param inputStream
     * @param filename
     */
    void upload(InputStream inputStream, String filename);

    /**
     * 文件上传
     * @param file
     */
    void upload(File file);

    /**
     * minIo客户端上传文件
     * @param inputStream 流
     * @param originalFilename 文件名
     * @param contentType 类型
     * @param mark 标记
     * @return 访问路径
     */
    String minIoUpload(InputStream inputStream, String originalFilename, String contentType, String mark);

    /**
     * 下载文件
     * @param fileName
     * @return
     */
    InputStream minIoDownload(String fileName) throws Exception;
}
