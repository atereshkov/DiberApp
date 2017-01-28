package com.github.handioq.diberapp.util;

import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.model.dto.OrderListDto;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.model.dvo.OrderListDvo;

public class Mapper {

    public static OrderListDvo mapOrderListToDvo(OrderListDto orderListDto) {
        OrderListDvo ordersDvo = new OrderListDvo();

        for (OrderDto orderDto : orderListDto.getOrders()) {
            ordersDvo.getOrders().add(mapOrderToDvo(orderDto));
        }

        return ordersDvo;
    }

    private static OrderDvo mapOrderToDvo(OrderDto orderDto) {
        return new OrderDvo(DateUtils.getStringDateFromTimestamp(orderDto.getDate()), orderDto.getDescription(),
                orderDto.getPrice(), orderDto.getStatus(), orderDto.getAddressFrom(), orderDto.getAddressTo());
    }

}
