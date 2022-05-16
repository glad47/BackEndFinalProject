package com.jugu.www.pcbonlinev2.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderComplaintsDTO {
    @NotNull(message = "id Can't be empty")
    private Integer id;

    @NotNull(message = "otype Can't be empty")
    private Integer otype;
}
