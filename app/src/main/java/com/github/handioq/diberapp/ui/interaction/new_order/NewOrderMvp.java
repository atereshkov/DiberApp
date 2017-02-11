/*
package com.github.handioq.diberapp.ui.interaction.new_order;

import com.github.handioq.diberapp.base.BaseMvp;

public interface NewOrderMvp {

    interface Model extends BaseMvp.Model {

        void addOrder();

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
*/
