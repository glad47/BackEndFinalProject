package com.jugu.www.pcbonlinev2.statemachine;

/**
 * 订单状态机
 */
public class OrderStateMachine implements StateMachine {

    @Override
    public State next(State state, Event event, Object context) {
        if (State.pending_audit.equals(state) && Event.audit.equals(event)){
            return State.pending_payment;
        }

        if (State.pending_payment.equals(state) && Event.pay.equals(event)){
            return State.already_paid;
        }

        if (State.already_paid.equals(state) && Event.production.equals(event)){
            return State.pending_shipped;
        }

        if(State.pending_shipped.equals(state) && Event.shipped.equals(event)){
            return State.already_shipped;
        }

        if(State.already_shipped.equals(state) && Event.receipt.equals(event)){
            return State.received;
        }

        return null;
    }
}
