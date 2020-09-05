package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class QuoteSize implements Serializable {
    private static final long serialVersionUID = -2521999265643280199L;
    @NotBlank(message = "sizeX不能为null")
    private String sizeX;
    @NotBlank(message = "sizeY不能为null")
    private String sizeY;
}
