package com.github.handioq.diberapp.ui.interaction.new_shop;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dvo.ShopDvo;

public interface NewShopMvp {

    interface Model extends BaseMvp.Model {

        void addShop(long userId, ShopDto shopDto);

        void setCallback(Callback callback);

        interface Callback {

            void onShopAdded(ShopDvo shopDvo);

            void onShopAddError(Throwable error);

            void onAddShopCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void showAddShopProgress();

        void hideAddShopProgress();

        void onShopAdded(ShopDvo shopDvo);

        void onAddShopError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<NewShopMvp.View> {

        void addShop(long userId, ShopDto shopDto);

    }

}
