package com.jugu.www.pcbonlinev2.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderAmountDTO implements Serializable {

    private static final long serialVersionUID = -5188477370262422171L;

    private String name;

    private String time;

    private BigDecimal amount;
}
