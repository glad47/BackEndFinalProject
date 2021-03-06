package com.jugu.www.pcbonlinev2.exception;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 类名称：GlobalExceptionHandler
 * ********************************
 * <p>
 * 类描述：全局异常捕获处理器
 *
 * @author
 * @date 下午9:21
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 拦截业务类异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult businessExceptionHandle(BusinessException e) {
        log.error("捕捉到业务类异常==>", e);

        return ResponseResult.failure(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResponseResult bindExceptionHandle(BindException bindException){
        log.error("捕捉到参数异常", bindException);
        StringBuilder errMsg = new StringBuilder();
        bindException.getAllErrors().forEach(x -> errMsg.append(x.getDefaultMessage()).append(" "));
        return  ResponseResult.failure(ErrorCodeEnum.PARAM_ERROR.getCode(),errMsg.toString().trim());
    }

    @ResponseBody
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseResult badCredentialsExceptionHandle(BadCredentialsException b){
        log.error("捕捉的异常",b);
        return ResponseResult.failure(ErrorCodeEnum.USER_PASS_ERROR);
    }


    /**
     * 拦截运行时异常
     * @param e
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult runtimeExceptionHandle(RuntimeException e) {
        log.error("捕捉到运行时异常：", e);

        return ResponseResult.failure(
                ErrorCodeEnum.UNKNOWN_ERROR.getCode(),
                e.getMessage());
    }

    /**
     * 捕捉系统级异常
     * @param th
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult throwableHandle(Throwable th) {
        log.error("捕捉Throwable异常：", th);

        return ResponseResult.failure(
                ErrorCodeEnum.SYSTEM_ERROR.getCode(),
                th.getMessage());
    }

}
