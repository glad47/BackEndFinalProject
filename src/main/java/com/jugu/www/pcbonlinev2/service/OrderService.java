package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.Result;
import com.jugu.www.pcbonlinev2.domain.dto.*;
import com.jugu.www.pcbonlinev2.domain.dto.order.*;
import com.jugu.www.pcbonlinev2.domain.entity.OrderDO;
import com.jugu.www.pcbonlinev2.domain.vo.InvoiceInfoVO;


import java.util.List;

/**
 * 订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface OrderService extends IService<OrderDO> {

    PageResult<List<OrderDTO>> queryPage(PageQuery<OrderQueryDTO, OrderDO> params);

    Result saveOrder(OrderSaveDTO orderSaveDTO);

    Result createOrder(PaymentParameterDTO paymentParameterDTO);

    String createOrderNo();

    /**
     * 获取到支付页面的信息
     * @param toPaymentParameterDTO 到支付页面回传参数
     */
    PaymentParameterDTO getToPaymentInfo(ToPaymentParameterDTO toPaymentParameterDTO);

    InvoiceInfoVO getInvoiceInfo(Integer orderId);

    Result payCard(CardPaymentDTO cardPaymentDTO);

    boolean auditOrderDetails(List<OrderDetails> orderDetailsList);

    void remarkUpdate(List<AuditOrderItem> orderDetailsList, String remark);

    QuoteInfoList queryQuoteItemByIds(OrderIds orderIds);

    void complaintsOrder(List<OrderComplaintsDTO> orderComplaintsList);
}

