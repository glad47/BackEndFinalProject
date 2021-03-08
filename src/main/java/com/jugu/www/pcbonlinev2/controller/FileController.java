package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.UserDTO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import com.jugu.www.pcbonlinev2.domain.vo.MinioUploadVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.FileService;
import com.jugu.www.pcbonlinev2.service.UserService;
import io.minio.errors.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 文件管理controller
 */
@RestController
@RequestMapping("/api/file")
@Slf4j
@Validated
@Api(value = "文件上传管理", tags = "文件上传controller", protocols = "http,https")
public class FileController extends BasicController<UserDO, UserDTO>{


    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Value("${pcbonline.img-access-url}")
    private String imgAccessUrl;

    @Value("${pcbonline.img-upload-dir}")
    private String imgUploadDir;

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
//            String url = fileService.minIoUpload(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), "img");
            String location = "/"+ DateUtils.formatDate(new Date(),"yyyy-MM-dd") + "/";
            File targetFile = new File(imgUploadDir+location);

            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            String filename = file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get(imgUploadDir+location,filename), StandardCopyOption.REPLACE_EXISTING);

            MinioUploadVO result = getMinIoUploadVO(file.getOriginalFilename(), imgAccessUrl+location+filename);

            //更新用户头像
            UserDO userDO = new UserDO();
            userDO.setId(getUserId());
            userDO.setFavicon(result.getUrl());

            userService.updateById(userDO);

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

    @ApiOperation(
            value = "下载文件",
            notes = "下载压缩包",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiImplicitParam(
            name = "filename",
            value = "20200909/xxxxxxxxx.zip",
            required = true,
            paramType = "quote",
            dataType = "string"
    )
    @GetMapping("/download/zip")
    public void downloadZip(@RequestParam("filename") String fileName, HttpServletResponse response) {
//        BufferedInputStream bis = null;
//        try {
//            InputStream obj = fileService.minIoDownload(fileName);
//
//            String[] split = fileName.split("/");
//            String fn = split[split.length - 1];
//            log.info("原先的路径->[{}],提取出的name->[{}]", fileName, fn);
//            byte[] buf = new byte[1024];
////            int length;
//            response.reset();
//
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fn, "UTF-8"));
//            response.setContentType("application/octet-stream");
//            response.setCharacterEncoding("utf-8");
//            OutputStream outputStream = response.getOutputStream();
//
//            bis = new BufferedInputStream(obj);
//            int i = bis.read(buf);
//            while (i != -1) {
//                outputStream.write(buf, 0, buf.length);
//                outputStream.flush();
//                i = bis.read(buf);
//            }
////            while ((length = obj.read(buf)) > 0) {
////                outputStream.write(buf, 0, length);
////            }
////            outputStream.close();
//        } catch (Exception e) {
//            log.error("下载文件出错", e);
//        } finally {
//            if (bis != null){
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        try (InputStream ism = new BufferedInputStream(fileService.minIoDownload(fileName))) {
            String[] split = fileName.split("/");
            String fn = split[split.length - 1];
            log.info("原先的路径->[{}],提取出的name->[{}]", fileName, fn);

            byte[] buf = new byte[1024];
            int length = 0;

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fn, "UTF-8"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            OutputStream osm = new BufferedOutputStream(response.getOutputStream());
            while ((length = ism.read(buf))>0){
                osm.write(buf,0,length);
            }
            osm.close();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (RegionConflictException e) {
            e.printStackTrace();
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidObjectPrefixException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }

}