package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

@Data
public class AssemblyFieldDTO {
    private String assemblySide;
    private Integer assemblyType;
    private Integer quantity;
    private Integer smtPartNum;
    private Integer throughHolePartNum;
    private Integer uniquePartNum;
}
