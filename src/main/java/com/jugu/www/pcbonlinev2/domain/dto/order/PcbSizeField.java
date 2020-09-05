package com.jugu.www.pcbonlinev2.domain.dto.order;

import com.jugu.www.pcbonlinev2.validator.PcbSizeFieldGroupSequenceProvider;
import com.jugu.www.pcbonlinev2.validator.group.PanelValidationGroup;
import lombok.Data;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.swing.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@GroupSequenceProvider(PcbSizeFieldGroupSequenceProvider.class)
public class PcbSizeField implements Serializable {
    private static final long serialVersionUID = -2125038869145258L;
    @NotNull(message = "boardType不能为null")
    private String boardType;
    @NotNull(message = "数量不能为null")
    private String quantity;
    @Valid
    @NotNull(message = "panelSize不能为null",groups = PanelValidationGroup.class)
    private QuoteSize panelSize;
    @Valid
    @NotNull(message = "singleSize不能为null",groups = {PanelValidationGroup.class, SingleSelectionModel.class})
    private QuoteSize singleSize;
}
