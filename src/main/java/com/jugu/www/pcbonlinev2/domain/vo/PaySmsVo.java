package com.jugu.www.pcbonlinev2.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class PaySmsVo {
    private String bn;
    private String un;
    private String pn;
    private BigDecimal total;
}
