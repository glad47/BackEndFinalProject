package com.jugu.www.pcbonlinev2.domain.vo;

import lombok.Data;

@Data
public class ReCaptchaVerifyVO {
    private Boolean success;
    private String challenge_ts;
    private String hostname;
}
