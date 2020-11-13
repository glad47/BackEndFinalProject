package com.jugu.www.pcbonlinev2.service.impl;

import com.jugu.www.pcbonlinev2.exception.BusinessException;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.FileService;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.policy.PolicyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    /**
     * 存储空间
     */
    private static final String BUCKET = "compressed";

    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;


    @Override
    public void upload(InputStream inputStream, String filename) {
        // 拼接文件的存储路径
        String storagePath = BUCKET + "/" + filename;

        try (
                // JDK8 TWR 不能关闭外部资源的
                InputStream innerInputStream = inputStream;

                FileOutputStream outputStream =
                        new FileOutputStream(new File(storagePath))
        ) {
            // 拷贝缓冲区
            byte[] buffer = new byte[1024];
            // 读取文件流长度
            int len;

            // 循环读取inputStream中数据写入到outputStream
            while ((len = innerInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }

            // 冲刷流
            outputStream.flush();

        } catch (Exception e) {

            log.error("文件上传失败！", e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }

    @Override
    public void upload(File file) {
        try {

            upload(new FileInputStream(file), file.getName());
        } catch (Exception e) {

            log.error("文件上传失败", e);
            throw new BusinessException(
                    ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }

    @Override
    public String minIoUpload(InputStream inputStream, String originalFilename, String contentType, String mark) {
        try {
            MinioClient minioClient = minIoClientFactory();
            String objectName = objectNameFactory(originalFilename,mark);
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCKET_NAME, objectName, inputStream, contentType);
            log.info("文件上传成功!");
            return ENDPOINT + "/" + BUCKET_NAME + "/" + objectName;
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new BusinessException(ErrorCodeEnum.UPLOAD_FILE_ERROR);
        }
    }

    @Override
    public InputStream minIoDownload(String fileName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, RegionConflictException, InvalidObjectPrefixException, InsufficientDataException, InvalidPortException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, InvalidEndpointException, ErrorResponseException, InvalidArgumentException {
        MinioClient minioClient = minIoClientFactory();
        return minioClient.getObject(BUCKET_NAME,fileName);
    }

    private String objectNameFactory(String originalFilename,String mark) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 设置存储对象名称
        return sdf.format(new Date()) + "/"+mark+"/"+ originalFilename;
    }

    /**
     * 根据不同业务方法创建不同配置的minIO客户端
     * @return minIo客户端
     */
    private MinioClient minIoClientFactory() throws InvalidEndpointException, InvalidPortException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, NoResponseException, XmlPullParserException, ErrorResponseException, InternalException, RegionConflictException, InvalidObjectPrefixException {
        MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
        if (minioClient.bucketExists(BUCKET_NAME)){
            log.info("存储捅已存在");
        }else {
            minioClient.makeBucket(BUCKET_NAME);//创建捅
            minioClient.setBucketPolicy(BUCKET_NAME,"*.*", PolicyType.READ_ONLY);//设为只读
        }
        return minioClient;
    }
}
