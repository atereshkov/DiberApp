package com.github.handioq.diberapp.ui.addresses.interaction;

import com.github.handioq.diberapp.base.BaseMvp;

public interface RemoveAddressMvp {

    interface Model extends BaseMvp.Model {

        void removeAddress(long userId, int addressId);

        void setCallback(Callback callback);

        interface Callback {

            void onAddressRemoved();

            void onAddressRemoveError(Throwable error);

            void onRemoveCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void onAddressRemoved();

        void onAddressRemoveError(Throwable e);

    }

    interface Presenter extends BaseMvp.Presenter<RemoveAddressMvp.View> {

        void removeAddress(long userId, int addressId);

    }

}
