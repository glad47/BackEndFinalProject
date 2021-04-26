package com.jugu.www.pcbonlinev2.domain.common;

import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "统一返回结果实体")
public class ResponseResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7813356989387725160L;

    /**
     * 是否成功
     */
    @ApiModelProperty(
            name = "success",
            value = "是否成功",
            required = true,
            dataType = "boolean"
    )
    private Boolean success; //是否成功

    /**
     * 编码
     */
    @ApiModelProperty(
            name = "code",
            value = "编码",
            required = false,
            dataType = "string"
    )
    private String code; //返回吗

    /**
     * 描述信息
     */
    @ApiModelProperty(
            value = "描述信息"
    )
    private String message; //描述信息

    /**
     * 结果
     */
    @ApiModelProperty(
            value = "泛型结果T"
    )
    private T result; //结果

    /**
     * 成功
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T result) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setSuccess(Boolean.TRUE);
        responseResult.setResult(result);
        responseResult.setCode("0");

        return responseResult;
    }

    /**
     * 失败
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(String code, String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setSuccess(Boolean.FALSE);
        responseResult.setCode(code);
        responseResult.setMessage(message);

        return responseResult;
    }

    /**
     * 失败
     * @param codeEnum
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(ErrorCodeEnum codeEnum) {
        return failure(codeEnum.getCode(), codeEnum.getMessage());
    }
}
