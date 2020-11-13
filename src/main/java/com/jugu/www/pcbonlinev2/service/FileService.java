package com.jugu.www.pcbonlinev2.service;

import io.minio.errors.*;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
    InputStream minIoDownload(String fileName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, RegionConflictException, InvalidObjectPrefixException, InsufficientDataException, InvalidPortException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, InvalidEndpointException, ErrorResponseException, InvalidArgumentException;
}
