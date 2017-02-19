package com.github.handioq.diberapp.ui.interaction.new_order;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.model.dvo.OrderDvo;

public interface NewOrderMvp {

    interface Model extends BaseMvp.Model {

        void addOrder(OrderDto orderDto);

        void setCallback(Callback callback);

        interface Callback {

            void onOrderAdded(OrderDvo orderDvo);

            void onOrderAddError(Throwable error);

            void onAddOrderCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void showAddOrderProgress();

        void hideAddOrderProgress();

        void onOrderAdded(OrderDvo orderDvo);

        void onAddOrderError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<NewOrderMvp.View> {

        void addOrder(OrderDto orderDto);

    }

}