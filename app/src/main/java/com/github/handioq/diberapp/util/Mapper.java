package com.github.handioq.diberapp.util;

import com.github.handioq.diberapp.model.dto.AddressDto;
import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.model.dto.PageableOrderListDto;
import com.github.handioq.diberapp.model.dto.RequestDto;
import com.github.handioq.diberapp.model.dto.ReviewDto;
import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dto.UserDto;
import com.github.handioq.diberapp.model.dvo.AddressDvo;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.model.dvo.OrderListDvo;
import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.model.dvo.ReviewDvo;
import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.model.dvo.UserDvo;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static OrderListDvo mapOrderListToDvo(PageableOrderListDto orderListDto) {
        OrderListDvo ordersDvo = new OrderListDvo();

        for (OrderDto orderDto : orderListDto.getOrders()) {
            ordersDvo.getOrders().add(mapOrderToDvo(orderDto));
        }

        return ordersDvo;
    }

    public static OrderDvo mapOrderToDvo(OrderDto orderDto) {
        String date = DateUtils.getStringDate(orderDto.getDate());

        AddressDvo addressToDvo = new AddressDvo();
        if (orderDto.getAddressTo() != null) {
            addressToDvo = mapAddressToDvo(orderDto.getAddressTo());
        }

        AddressDvo addressFromDvo = new AddressDvo();
        if (orderDto.getAddressFrom() != null) {
            addressFromDvo = mapAddressToDvo(orderDto.getAddressFrom());
        }

        UserDvo customerDvo;
        if (orderDto.getCustomer() != null) {
            customerDvo = mapUserToDvo(orderDto.getCustomer());
        } else {
            customerDvo = null;
        }

        UserDvo courierDvo;
        if (orderDto.getCourier() != null) {
            courierDvo = mapUserToDvo(orderDto.getCourier());
        } else {
            courierDvo = null;
        }

        return new OrderDvo(orderDto.getId(), date, orderDto.getDescription(),
                orderDto.getPrice(), orderDto.getStatus(), addressToDvo, addressFromDvo, courierDvo, customerDvo);
    }

    public static UserDvo mapUserToDvo(UserDto userDto) {
        return new UserDvo(userDto.getId(), userDto.getEmail(), userDto.getUsername(), userDto.isEnabled(), userDto.getFullname());
    }

    public static List<AddressDvo> mapAddressListToDvo(List<AddressDto> addressListDto) {
        List<AddressDvo> addressesDvo = new ArrayList<>();

        for (AddressDto addressDto : addressListDto) {
            addressesDvo.add(mapAddressToDvo(addressDto));
        }

        return addressesDvo;
    }

    public static AddressDvo mapAddressToDvo(AddressDto addressDto) {
        return new AddressDvo(addressDto.getId(), addressDto.getName(), addressDto.getPostalCode(), addressDto.getCountry(), addressDto.getCity(),
                addressDto.getRegion(), addressDto.getAddress(), addressDto.getPhone());
    }

    public static List<RequestDvo> mapRequestsToDvo(List<RequestDto> requestListDto) {
        List<RequestDvo> requestsDvo = new ArrayList<>();

        for (RequestDto requestDto : requestListDto) {
            requestsDvo.add(mapRequestToDvo(requestDto));
        }

        return requestsDvo;
    }

    public static RequestDvo mapRequestToDvo(RequestDto requestDto) {
        return new RequestDvo(requestDto.getId(), mapOrderToDvo(requestDto.getOrder()),
                mapUserToDvo(requestDto.getCourier()), requestDto.getStatus());
    }

    public static List<ReviewDvo> mapReviewsToDvo(List<ReviewDto> reviews) {
        List<ReviewDvo> reviewsDvo = new ArrayList<>();

        for (ReviewDto reviewDto : reviews) {
            reviewsDvo.add(mapReviewToDvo(reviewDto));
        }

        return reviewsDvo;
    }

    public static ReviewDvo mapReviewToDvo(ReviewDto reviewDto) {
        return new ReviewDvo(reviewDto.getReview(), reviewDto.getRating(), mapUserToDvo(reviewDto.getCourer()), mapUserToDvo(reviewDto.getUser()));
    }

    public static AddressDto mapAddressToDto(AddressDvo addressDvo) {
        return new AddressDto(addressDvo.getName(), addressDvo.getPostalCode(), addressDvo.getCountry(), addressDvo.getCity(),
                addressDvo.getRegion(), addressDvo.getAddress(), addressDvo.getPhone());
    }
}
