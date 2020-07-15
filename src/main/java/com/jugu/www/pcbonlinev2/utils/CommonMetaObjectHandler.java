package com.jugu.www.pcbonlinev2.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 公共元数据处理器
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    private final static String DEFAULT_FAVICON = "https://www.pcbonline.com/file/2019-05-16/head.jpg";

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert data , 填充数据");
        this.strictInsertFill(metaObject,"gmtCreate", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"gmtModified", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"favicon",String.class,DEFAULT_FAVICON);
        this.strictInsertFill(metaObject, "uuid", String.class, UUID.randomUUID().toString());
        this.strictInsertFill(metaObject,"userIp",String.class,IPUtils.getIpAddr(HttpContextUtil.getHttpServletRequest()));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update data, 更新数据");

        this.strictUpdateFill(metaObject,"gmtModified", LocalDateTime.class,LocalDateTime.now());
    }
}
