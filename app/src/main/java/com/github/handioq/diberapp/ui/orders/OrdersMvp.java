package com.github.handioq.diberapp.ui.orders;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dvo.OrderListDvo;

public interface OrdersMvp {

    interface Model extends BaseMvp.Model {

        void getOrders(int offset, int count);

        void setCallback(Callback callback);

        interface Callback {

            void onOrdersLoaded(OrderListDvo orders);

            void onOrdersLoadError(Throwable error);

            void onLoadOrdersCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void showLoadOrdersProgress();

        void hideLoadOrdersProgress();

        void setOrders(OrderListDvo orders);

        void showLoadOrdersError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<OrdersMvp.View> {

        void getOrders(int offset, int limit);

    }
}
