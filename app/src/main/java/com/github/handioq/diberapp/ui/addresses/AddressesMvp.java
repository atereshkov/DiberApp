package com.github.handioq.diberapp.ui.addresses;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dvo.AddressDvo;

import java.util.List;

public interface AddressesMvp {

    interface Model extends BaseMvp.Model {

        void getUserAddresses(long userId);

        void setCallback(Callback callback);

        interface Callback {

            void onAddressesLoaded(List<AddressDvo> addresses);

            void onAddressesLoadError(Throwable error);

            void onLoadAddressesCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void showLoadAddressesProgress();

        void hideLoadAddressesProgress();

        void setAddresses(List<AddressDvo> addresses);

        void showLoadAddressesError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<AddressesMvp.View> {

        void getUserAddresses(long userId);

    }

}
