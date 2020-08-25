package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.vo.MinioUploadVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.FileService;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件管理controller
 */
@RestController
@RequestMapping("/api/file")
@Slf4j
public class FileController {

    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseResult upload(@NotNull(message = "文件不能为空") @RequestParam("file") MultipartFile file) {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            if (minioClient.bucketExists(BUCKET_NAME)){
                log.info("存储捅已存在");
            }else {
                minioClient.makeBucket(BUCKET_NAME);//创建捅
                minioClient.setBucketPolicy(BUCKET_NAME,"*.*", PolicyType.READ_ONLY);//设为只读
            }
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
            log.info("文件上传成功!");

            MinioUploadVO result = new MinioUploadVO();
            result.setName(filename);
            result.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);

            return ResponseResult.success(result);
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage());
        }

        return ResponseResult.failure(ErrorCodeEnum.UPLOAD_FILE_ERROR);
    }
}
