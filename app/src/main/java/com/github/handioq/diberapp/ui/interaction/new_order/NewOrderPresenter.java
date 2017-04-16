package com.github.handioq.diberapp.ui.interaction.new_order;

import com.github.handioq.diberapp.model.dto.NewOrderDto;
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
        if (view != null) {
            view.onOrderAdded(orderDvo);
            view.hideAddOrderProgress();
        }
    }

    @Override
    public void onOrderAddError(Throwable error) {
        if (view != null) {
            view.onAddOrderError(error);
            view.hideAddOrderProgress();
        }
    }

    @Override
    public void onAddOrderCompleted() {
        view.hideAddOrderProgress();
    }

    @Override
    public void addOrder(long userId, NewOrderDto orderDto) {
        if (view != null) {
            view.showAddOrderProgress();
        }

        model.addOrder(userId, orderDto);
    }

    @Override
    public void setView(NewOrderMvp.View view) {
        this.view = view;
    }

}