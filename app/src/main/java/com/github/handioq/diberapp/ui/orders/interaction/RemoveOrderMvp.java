package com.github.handioq.diberapp.ui.orders.interaction;

import com.github.handioq.diberapp.base.BaseMvp;

public interface RemoveOrderMvp {

    interface Model extends BaseMvp.Model {

        void deleteOrder(long orderId);

        void setCallback(Callback callback);

        interface Callback {

            void onOrderRemoved();

            void onOrderRemoveError(Throwable error);

            void onRemoveCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void onOrderRemoved();

        void onOrderRemoveError(Throwable e);

    }

    interface Presenter extends BaseMvp.Presenter<RemoveOrderMvp.View> {

        void deleteOrder(long orderId);

    }

}
