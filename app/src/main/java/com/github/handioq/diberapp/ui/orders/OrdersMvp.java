package com.github.handioq.diberapp.ui.orders;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dvo.OrderDvo;

import java.util.List;

public interface OrdersMvp {

    interface Model extends BaseMvp.Model {

        void getOrders(long userId);

        void setCallback(Callback callback);

        interface Callback {

            void onOrdersLoaded(List<OrderDvo> orders);

            void onOrdersLoadError(Throwable error);

            void onLoadOrdersCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void showLoadOrdersProgress();

        void hideLoadOrdersProgress();

        void setOrders(List<OrderDvo> orders);

        void showLoadOrdersError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<OrdersMvp.View> {

        void getOrders(long userId);

    }
}
