package com.jugu.www.pcbonlinev2.state.core;

import lombok.Data;

@Data
abstract class AbstractStateMachine implements StateMachines{
    /**
     * 状态机名字
     */
    String stateMachineName;

}
