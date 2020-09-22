package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

@Data
public class StencilField {
    private Integer quantity;
    private String stencilSide;
    private String thickness;
    private String existingFiducials;
    private String dimensions;
    private QuoteStencil detailed;
}
