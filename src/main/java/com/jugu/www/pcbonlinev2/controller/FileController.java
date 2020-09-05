package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.vo.MinioUploadVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.FileService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * 文件管理controller
 */
@RestController
@RequestMapping("/api/file")
@Slf4j
@Validated
@Api(value = "文件上传管理", tags = "文件上传controller", protocols = "http,https")
public class FileController {


    @Autowired
    private FileService fileService;

    @ApiOperation(
            value = "上传图片",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "file",
            value = "上传文件",
            required = true,
            paramType = "query",
            dataType = "string"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/upload/img")
    public ResponseResult upload(@NotNull(message = "文件不能为空") @RequestParam("file") MultipartFile file) {
        try {
            String url = fileService.minIoUpload(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), "img");
            MinioUploadVO result = getMinIoUploadVO(file.getOriginalFilename(), url);
            return ResponseResult.success(result);
        } catch (IOException e) {
            log.error("上传文件失败: ",e);
        }

        return ResponseResult.failure(ErrorCodeEnum.UPLOAD_FILE_ERROR);
    }


    @ApiOperation(
            value = "上传压缩包",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "file",
            value = "上传文件",
            required = true,
            paramType = "body",
            dataType = "string"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/upload/zip")
    public ResponseResult uploadZip(@NotNull(message = "文件不能为空") @RequestParam("file") MultipartFile file) {
        try {
            String url = fileService.minIoUpload(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), "zip");
            return ResponseResult.success(getMinIoUploadVO(file.getOriginalFilename(), url));
        } catch (IOException e) {
            log.error("上传文件失败: ", e);
        }
        return ResponseResult.failure(ErrorCodeEnum.UPLOAD_FILE_ERROR);
    }


    private MinioUploadVO getMinIoUploadVO(String fileName, String url) {
        MinioUploadVO result = new MinioUploadVO();
        result.setName(fileName);
        result.setUrl(url);
        return result;
    }

}