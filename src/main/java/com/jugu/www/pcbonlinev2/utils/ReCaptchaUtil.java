package com.jugu.www.pcbonlinev2.utils;

import com.jugu.www.pcbonlinev2.domain.vo.ReCaptchaVerifyVO;
import com.jugu.www.pcbonlinev2.exception.BusinessException;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * reCaptcha token校验
 */
@Slf4j
@Component
public class ReCaptchaUtil {
    //    private static final String SECRET = "6LdM76QZAAAAADtXY3RdANu4AyRd5hWFIheaIXey";
    private static final String SECRET = "6Lfm3LAZAAAAACkO3ZXYehB5nGqlY_9cVrWXwVFX";
    private static final String GOOGLE_API = "https://www.google.com/recaptcha/api/siteverify";


    public ReCaptchaVerifyVO verifyToken(String token) {
        try {
            RestTemplate client = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

            params.add("secret", SECRET);
            params.add("response", token);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);
            ResponseEntity<ReCaptchaVerifyVO> responseEntity = client.exchange(GOOGLE_API, HttpMethod.POST, requestEntity, ReCaptchaVerifyVO.class);

            log.info("google-ReCaptcha 结果===>【{}】", responseEntity.getBody());
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error("验证reCaptcha", e);
            throw new BusinessException(ErrorCodeEnum.RE_CAPTCHA_ERROR,e.getMessage());
        }
    }
}
