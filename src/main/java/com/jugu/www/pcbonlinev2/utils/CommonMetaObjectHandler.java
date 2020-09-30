package com.jugu.www.pcbonlinev2.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
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
        this.strictInsertFill(metaObject,"gmtCreate", Date.class,new Date());
        this.strictInsertFill(metaObject,"gmtModified", Date.class,new Date());
        this.strictInsertFill(metaObject,"favicon",String.class,DEFAULT_FAVICON);
        this.strictInsertFill(metaObject, "uuid", String.class, UUID.randomUUID().toString());
        this.strictInsertFill(metaObject,"userIp",String.class,IPUtils.getIpAddr(HttpContextUtil.getHttpServletRequest()));
        this.strictInsertFill(metaObject,"userId",Integer.class,ThreadSessionLocal.getUserInfo().getId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update data, 更新数据");

        this.strictUpdateFill(metaObject,"gmtModified", Date.class,new Date());
    }
}
