package com.github.handioq.diberapp.base.event;

import com.github.handioq.diberapp.model.dvo.OrderDvo;

public class RemoveOrderEvent {

    private final OrderDvo orderDvo;

    public RemoveOrderEvent(OrderDvo orderDvo) {
        this.orderDvo = orderDvo;
    }

    public OrderDvo getOrderDvo() {
        return orderDvo;
    }

}