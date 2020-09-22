package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuoteStencil {
    private Integer id;
    private Integer stencilSizex;
    private Integer stencilSizey;
    private Integer stencilAreax;
    private Integer stencilAreay;
    private BigDecimal price;
    private BigDecimal priceToUSD;
    private BigDecimal weight;
    private String materialName;
}
