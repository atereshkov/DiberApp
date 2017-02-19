package com.github.handioq.diberapp.ui.interaction.new_order;

import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class NewOrderPresenter implements NewOrderMvp.Presenter, NewOrderMvp.Model.Callback {

    private final static String TAG = "NewOrderPresenter";

    private NetworkService networkService;
    private NewOrderMvp.View view;
    private NewOrderModel model;

    @Inject
    public NewOrderPresenter(NetworkService networkService) {
        model = new NewOrderModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void onOrderAdded(OrderDvo orderDvo) {

    }

    @Override
    public void onOrderAddError(Throwable error) {

    }

    @Override
    public void onAddOrderCompleted() {

    }

    @Override
    public void addOrder(OrderDto orderDto) {

    }

    @Override
    public void setView(NewOrderMvp.View view) {
        this.view = view;
    }
}
