package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuoteSubtotal {
    private BigDecimal boardFee;
    private BigDecimal engineeringFee;
    private BigDecimal shippingFee;
    private BigDecimal testFee;
    private BigDecimal totalWeight;
    private BigDecimal urgentFee;
    private BigDecimal stencilFee;
    private BigDecimal assemblyFee;
    private String buildTime;
}
