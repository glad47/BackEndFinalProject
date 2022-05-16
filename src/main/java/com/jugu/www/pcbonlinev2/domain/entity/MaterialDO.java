package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 材料表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("material")
public class MaterialDO implements Serializable {
    // TODO serialVersionUid
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 材料名
     */
    private String name;

}
