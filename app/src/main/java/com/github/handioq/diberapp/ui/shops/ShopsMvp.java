package com.github.handioq.diberapp.ui.shops;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dvo.ShopDvo;

import java.util.List;

public interface ShopsMvp {

    interface Model extends BaseMvp.Model {

        void getUserShops(long userId);

        void setCallback(Callback callback);

        interface Callback {

            void onShopsLoaded(List<ShopDvo> shops);

            void onShopsLoadError(Throwable error);

            void onLoadShopsCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void showLoadShopsProgress();

        void hideLoadShopsProgress();

        void setShops(List<ShopDvo> shops);

        void showLoadShopsError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<ShopsMvp.View> {

        void getUserShops(long userId);

    }

}
