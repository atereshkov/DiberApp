package com.github.handioq.diberapp.ui.shops.interaction;

import com.github.handioq.diberapp.base.BaseMvp;

public interface RemoveShopMvp {

    interface Model extends BaseMvp.Model {

        void removeShop(long userId, int shopId);

        void setCallback(Callback callback);

        interface Callback {

            void onShopRemoved();

            void onShopRemoveError(Throwable error);

            void onRemoveCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void onShopRemoved();

        void onShopRemoveError(Throwable e);

    }

    interface Presenter extends BaseMvp.Presenter<RemoveShopMvp.View> {

        void removeShop(long userId, int shopId);

    }

}
