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
    private String boardType;
    private Integer quantity;
    private QuoteSize panelSize;
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
