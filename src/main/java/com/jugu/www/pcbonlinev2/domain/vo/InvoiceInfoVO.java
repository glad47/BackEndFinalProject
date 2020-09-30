package com.jugu.www.pcbonlinev2.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class InvoiceInfoVO implements Serializable {

    private static final long serialVersionUID = -1639397105120913409L;

    /**
     * pcb数据
     */
    private List<QuoteVO> quoteVOList;

    /**
     * 钢网数据
     */
    private List<SmlStencilVO> smlStencilVOS;

    /**
     * 贴片数据
     */
    private List<AssemblyVO> assemblyVOList;

    /**
     * 订单数据
     */
    private OrderVO orderVO;

    /**
     * 送货地址
     */
    private ReceiverAddersVO receiverAddersVO;

}
