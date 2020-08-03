package com.jugu.www.pcbonlinev2.statehandler;

import com.jugu.www.pcbonlinev2.domain.dto.OrderDTO;
import com.jugu.www.pcbonlinev2.statemachine.Event;
import com.jugu.www.pcbonlinev2.statemachine.State;

import java.util.HashMap;
import java.util.Map;

public class StateMachineEngine {
    private static Map<String, StateHandler> holder;

    public static void post(OrderDTO orderDTO, Event event){
        StateHandler stateHandler = getHandler(orderDTO.getState(), event);
        if (null == stateHandler) throw new IllegalStateException();

        stateHandler.handler(orderDTO);
    }

    private static StateHandler getHandler(State state, Event event) {
        if (null == holder){
            synchronized (StateMachineEngine.class){
                if (null == holder){
                    init();
                }
            }
        }
        String key = state.name() + ":" + event.name();
        return holder.get(key);
    }

    private static void init(){
        holder = new HashMap<>();
        holder.put("pending_payment:pay",new UserPayHandler());
    }

    public static void main(String[] args) {
        OrderDTO orderDTO = new OrderDTO();
        StateMachineEngine.post(orderDTO,Event.pay);
    }
}
