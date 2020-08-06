package com.jugu.www.pcbonlinev2.state.handler;

import com.jugu.www.pcbonlinev2.state.constant.State;
import lombok.Data;

/**
 * 抽象订单操作类
 */
@Data
public abstract class AbstractOrderOperator{
    /**
     * 状态
     */
    int status;

    /**
     * 根据当前状态和行为得到下个状态
     * @param orderStatus 订单当前状态
     * @param state 行为
     * @return 下一个状态
     */
    public abstract int handleEvent(int orderStatus, State state);

}
