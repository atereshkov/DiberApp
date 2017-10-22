package com.github.handioq.diberapp.ui.order;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dvo.OrderDvo;

public interface OrderMvp {

    interface Model extends BaseMvp.Model {

        void getOrder(long orderId);

        void setCallback(Callback callback);

        interface Callback {

            void onOrderLoaded(OrderDvo order);

            void onOrderLoadError(Throwable error);

            void onOrderLoadCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void setOrder(OrderDvo order);

        void showLoadOrderProgress();

        void hideLoadOrderProgress();

        void showLoadOrderError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<OrderMvp.View> {

        void getOrder(long orderId);

    }

}