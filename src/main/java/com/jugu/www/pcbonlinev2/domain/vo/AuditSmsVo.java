package com.jugu.www.pcbonlinev2.domain.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuditSmsVo {
    private String orderType;

    private String orderNo;
}
