package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 信息消息
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class MessageDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "发送人")
    private String sendUser; //发送人
    @ApiModelProperty(value = "接收人")
    private String receiveUser; //接收人
    @ApiModelProperty(value = "群id")
    private String groupId; //群id
    @ApiModelProperty(value = "是否已读（0未读 1 已读）")
    private Integer isread; //是否已读（0未读 1 已读）
    @ApiModelProperty(value = "类型（0 单聊 1 群聊）")
    private Integer type; //类型（0 单聊 1 群聊）
    @ApiModelProperty(value = "消息内容")
    private String content; //消息内容
    @ApiModelProperty(value = "创建用户")
    private Integer createUser; //创建用户
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified; //修改时间
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate; //创建时间

}
