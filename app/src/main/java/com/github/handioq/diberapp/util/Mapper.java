package com.github.handioq.diberapp.util;

import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.model.dvo.OrderDvo;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<OrderDvo> mapOrderListToDvo(List<OrderDto> orderListDto) {
        List<OrderDvo> ordersDvo = new ArrayList<>();

        for (OrderDto orderDto : orderListDto) {
            ordersDvo.add(mapOrderToDvo(orderDto));
        }

        return ordersDvo;
    }

    private static OrderDvo mapOrderToDvo(OrderDto orderDto) {
        return new OrderDvo(DateUtils.getStringDateFromTimestamp(orderDto.getDate()), orderDto.getDescription(),
                orderDto.getPrice(), orderDto.getStatus(), orderDto.getAddressFrom(), orderDto.getAddressTo());
    }

}
