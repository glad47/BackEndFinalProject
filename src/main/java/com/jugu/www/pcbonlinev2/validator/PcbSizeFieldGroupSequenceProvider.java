package com.jugu.www.pcbonlinev2.validator;

import com.jugu.www.pcbonlinev2.domain.dto.order.PcbSizeField;
import com.jugu.www.pcbonlinev2.validator.group.PanelValidationGroup;
import com.jugu.www.pcbonlinev2.validator.group.SingleValidationGroup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PcbSizeFieldGroupSequenceProvider implements DefaultGroupSequenceProvider<PcbSizeField> {

    @Override
    public List<Class<?>> getValidationGroups(PcbSizeField bean) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(PcbSizeField.class);
        if (bean != null){
            String boardType = bean.getBoardType();
            log.info("板类型为-->[{}]", boardType);
            if (boardType.equals("Single")){
                defaultGroupSequence.add(SingleValidationGroup.class);
            }else if(boardType.equals("Panel")){
                defaultGroupSequence.add(PanelValidationGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
