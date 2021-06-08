package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

import java.util.List;

/**
 * 风险对象
 */
@Data
public class RiskInfo {
    private String adjustment_factor; //风险分数调整，可为负数。分数为整数
    private String retry_num; //商户在其他金融机构和在 晶粒 重试的总次数
    private Trade trade; //贸易信息
    private Device device; //设备信息
    private Cust cust; //用户参数
    private List<Goods> goods; //商品信息
    private List<Buried> buried; //埋点数据
    private Ship ship; //收货地址
    private Bill bill; //账单信息
}
