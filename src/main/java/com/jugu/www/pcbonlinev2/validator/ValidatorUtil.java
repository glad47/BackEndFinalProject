package com.jugu.www.pcbonlinev2.validator;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 类名称：ValidatorUtil
 * ********************************
 * <p>
 * 类描述：校验工具类
 *
 * @author
 * @date 上午8:49
 */
public class ValidatorUtil {

    /**
     * 校验器
     */
    private static Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();

    /**
     * 参数校验
     * @param object
     * @param groups
     * @param <T>
     */
    public static <T> void validate(T object, Class... groups) {
        Set<ConstraintViolation<T>> validate =
                validator.validate(object, groups);

        // 如果校验结果不为空
        if (!CollectionUtils.isEmpty(validate)) {
            StringBuilder exceptionMessage = new StringBuilder();
            validate.forEach(constraintViolation -> {
                exceptionMessage.append(constraintViolation.getMessage());
            });

            throw new RuntimeException(exceptionMessage.toString());
        }
    }

}
