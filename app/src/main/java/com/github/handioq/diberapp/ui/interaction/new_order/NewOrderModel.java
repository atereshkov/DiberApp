package com.github.handioq.diberapp.ui.interaction.new_order;

import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.network.NetworkService;

public class NewOrderModel implements NewOrderMvp.Model {

    private final static String TAG = "NewOrderModel";

    private final NetworkService networkService;
    private NewOrderModel.Callback callback;

    public NewOrderModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void addOrder(OrderDto orderDto) {

    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
