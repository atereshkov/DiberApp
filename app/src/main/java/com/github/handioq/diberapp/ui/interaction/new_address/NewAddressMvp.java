package com.github.handioq.diberapp.ui.interaction.new_address;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dto.AddressDto;
import com.github.handioq.diberapp.model.dvo.AddressDvo;

public interface NewAddressMvp {

    interface Model extends BaseMvp.Model {

        void addAddress(long userId, AddressDto addressDto);

        void setCallback(Callback callback);

        interface Callback {

            void onAddressAdded(AddressDvo addressDvo);

            void onAddressAddError(Throwable error);

            void onAddAddressCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void showAddAddressProgress();

        void hideAddAddressProgress();

        void onAddressAdded(AddressDvo addressDvo);

        void onAddAddressError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<NewAddressMvp.View> {

        void addAddress(long userId, AddressDto addressDto);

    }

}