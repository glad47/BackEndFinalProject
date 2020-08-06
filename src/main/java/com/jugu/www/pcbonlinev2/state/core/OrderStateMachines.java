package com.jugu.www.pcbonlinev2.state.core;

import com.jugu.www.pcbonlinev2.state.annotation.StateMachine;
import com.jugu.www.pcbonlinev2.state.constant.Event;
import com.jugu.www.pcbonlinev2.state.constant.State;
import org.springframework.stereotype.Component;

@Component
@StateMachine
public class OrderStateMachines extends AbstractStateMachine {
    public OrderStateMachines() {
        super.setStateMachineName("order");
    }

    @Override
    public State next(State currState, Event event) {

        if (State.TEMPORARY_ORDER.equals(currState) && Event.CREATE_EVENT.equals(event)){
            return State.FORMAL_ORDER;
        }

        if (State.FORMAL_ORDER.equals(currState) && Event.PAY_EVENT.equals(event)){
            return State.PAY_DONE;
        }

        if (State.FORMAL_ORDER.equals(currState) && Event.CANCEL_EVENT.equals(event)){
            return State.ORDER_CANCEL;
        }

        if(State.PAY_DONE.equals(currState) && Event.SHIPPING_EVENT.equals(event)){
            return State.PENDING_RECEIVED;
        }

        if(State.PENDING_RECEIVED.equals(currState) && Event.RECEIVING_EVENT.equals(event)){
            return State.ORDER_FINISHED;
        }

        throw  new IllegalStateException();
    }
}
