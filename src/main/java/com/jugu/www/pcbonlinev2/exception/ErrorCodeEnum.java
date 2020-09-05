package com.jugu.www.pcbonlinev2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类名称：ErrorCodeEnum
 * ********************************
 * <p>
 * 类描述：异常编码枚举
 *
 * @author
 * @date 2020/3/1 下午9:54
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    // 0*** 成功
    SUCCESS("0000", "操作成功"),

    //授权异常
    AUTH_ACCESS_DENIED("403", "token已失效,Access Denied !!(拒绝访问)"),
    AUTH_UNAUTHORISED("401", "Unauthorised !! (未经授权的)"),

    // 1*** 参数异常
    PARAM_ERROR("1001", "参数异常"),
    PARAM_NULL("1002", "参数为空"),
    PARAM_FORMAT_ERROR("1003", "参数格式不正确"),
    PARAM_VALUE_ERROR("1004", "参数值不正确"),
    PARAM_AUTH_NULL("1005", "参数Authorization在Header中为空或token失效！！"),
    PARAM_AUTH_ERROR("1006", "token失效或已过期"),
    PARAM_EMAIL_ERROR("1007", "邮箱已存在"),
    PARAM_BUSINESS_CODE_ERROR("1008", "无效的邀请码，跟单员前缀无效！！"),

    // 2*** 系统异常
    SYSTEM_ERROR("2001", "服务异常"),
    UNKNOWN_ERROR("2002", "未知异常"),
    USER_PASS_ERROR("2003", "用户名或密码错误"),
    USER_NOT_ERROR("2005", "用户邮箱不存在"),
    RE_CAPTCHA_ERROR("2004", "google-RE-CAPTCHA校验失败"),
    USER_LOGIN_INFO_NULL("2005", "用户信息查询为null"),
    UPLOAD_FILE_ERROR("2006","上传文件失败"),

    // 3*** 业务异常
    XXX("3001", "业务异常"),
    INSERT_FAILURE("3002", "新增失败"),
    UPDATE_FAILURE("3003", "更新失败"),
    DELETE_FAILURE("3004", "删除失败"),
    RATE_LIMIT_ERROR("3005", "限流异常"),
    FILE_UPLOAD_FAILURE("3006", "文件上传失败");


    /**
     * 错误编码
     */
    private String code;

    /**
     * 错误描述
     */
    private String message;


}


