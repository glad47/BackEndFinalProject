package com.jugu.www.pcbonlinev2.domain.dto;


import com.jugu.www.pcbonlinev2.domain.dto.order.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "订单保存数据")
public class OrderSaveDTO implements Serializable {


    private static final long serialVersionUID = 970107995242103321L;
//    @Valid
    private PcbSizeField pcbSizeField;
    private PcbSpecialField pcbSpecialField;
    private PcbStandardField pcbStandardField;
    private QuoteSubtotal subtotal;
    private StencilField stencilField;
    private AssemblyFieldDTO assemblyField;

    private Boolean isExistPcb = false; //是否存在pcb
    private Integer pcbId; //pcbid
    private String pno; //客户型号

    @NotBlank(message = "文件名不能为null")
    private String fileName; //gerber文件名
    @NotBlank(message = "文件上传路径不能为null")
    private String fileUploadPtah; //gerber上传后路径
}
