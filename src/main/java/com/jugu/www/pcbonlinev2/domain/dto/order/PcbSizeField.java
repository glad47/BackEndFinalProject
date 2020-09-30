package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PcbSizeField implements Serializable {
    private static final long serialVersionUID = -2125038869145258L;
//    @NotNull(message = "boardType不能为null")
    private String boardType;
//    @NotNull(message = "数量不能为null")
//    @DecimalMin(value = "1",message = "数量只能是数字，且必须大于1")
    private Integer quantity;

//    @NotNull(message = "singleSize与panelSize的sizeX和sizeY不能为空")
    private QuoteSize panelSize;
//    @NotNull(message = "singleSize的sizeX和sizeY不能为空")
    private QuoteSize singleSize;

//    @AssertTrue
//    private boolean isSingleSize() {
//        if (this.boardType.equals("Single"))
//            return !StringUtils.isEmpty(this.singleSize.getSizeX()) && !StringUtils.isEmpty(this.singleSize.getSizeY());
//        return true;
//    }
//
//    @AssertTrue
//    private boolean isPanelSize(){
//        if (this.boardType.equals("Panel"))
//            return !StringUtils.isEmpty(this.singleSize.getSizeX()) && !StringUtils.isEmpty(this.singleSize.getSizeY()) && !StringUtils.isEmpty(this.panelSize.getSizeX()) &&!StringUtils.isEmpty(this.panelSize.getSizeX());
//        return true;
//    }

}
