package com.github.handioq.diberapp.util;

import com.github.handioq.diberapp.model.dto.AddressDto;
import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dto.UserDto;
import com.github.handioq.diberapp.model.dvo.AddressDvo;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.model.dvo.UserDvo;

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

    public static UserDvo mapUserToDvo(UserDto userDto) {
        return new UserDvo(userDto.getEmail(), userDto.getUsername(), userDto.isEnabled(), userDto.getFullname());
    }

    public static List<AddressDvo> mapAddressListToDvo(List<AddressDto> addressListDto) {
        List<AddressDvo> addressesDvo = new ArrayList<>();

        for (AddressDto addressDto : addressListDto) {
            addressesDvo.add(mapAddressToDvo(addressDto));
        }

        return addressesDvo;
    }

    private static AddressDvo mapAddressToDvo(AddressDto addressDto) {
        return new AddressDvo(addressDto.getName(), addressDto.getPostalCode(), addressDto.getCountry(), addressDto.getCity(),
                addressDto.getRegion(), addressDto.getAddress(), addressDto.getPhone());
    }

    public static List<ShopDvo> mapShopsListToDvo(List<ShopDto> shopListDto) {
        List<ShopDvo> shopDvo = new ArrayList<>();

        for (ShopDto shopDto : shopListDto) {
            shopDvo.add(mapShopToDvo(shopDto));
        }

        return shopDvo;
    }

    private static ShopDvo mapShopToDvo(ShopDto shopDto) {
        return new ShopDvo(shopDto.getName(), shopDto.getAddress());
    }
}
