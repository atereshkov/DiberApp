package com.github.handioq.diberapp.di.module;

import com.github.handioq.diberapp.di.scope.PresenterScope;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.ui.addresses.AddressesMvp;
import com.github.handioq.diberapp.ui.addresses.AddressesPresenter;
import com.github.handioq.diberapp.ui.addresses.interaction.RemoveAddressMvp;
import com.github.handioq.diberapp.ui.addresses.interaction.RemoveAddressPresenter;
import com.github.handioq.diberapp.ui.auth.login.LoginMvp;
import com.github.handioq.diberapp.ui.auth.login.LoginPresenter;
import com.github.handioq.diberapp.ui.auth.registration.SignupMvp;
import com.github.handioq.diberapp.ui.auth.registration.SignupPresenter;
import com.github.handioq.diberapp.ui.interaction.new_address.NewAddressMvp;
import com.github.handioq.diberapp.ui.interaction.new_address.NewAddressPresenter;
import com.github.handioq.diberapp.ui.interaction.new_order.NewOrderMvp;
import com.github.handioq.diberapp.ui.interaction.new_order.NewOrderPresenter;
import com.github.handioq.diberapp.ui.order.OrderMvp;
import com.github.handioq.diberapp.ui.order.OrderPresenter;
import com.github.handioq.diberapp.ui.orders.OrdersMvp;
import com.github.handioq.diberapp.ui.orders.OrdersPresenter;
import com.github.handioq.diberapp.ui.orders.interaction.RemoveOrderMvp;
import com.github.handioq.diberapp.ui.orders.interaction.RemoveOrderPresenter;
import com.github.handioq.diberapp.ui.request.RequestMvp;
import com.github.handioq.diberapp.ui.request.RequestPresenter;
import com.github.handioq.diberapp.ui.request.interaction.AcceptRequestMvp;
import com.github.handioq.diberapp.ui.request.interaction.AcceptRequestPresenter;
import com.github.handioq.diberapp.ui.requests.RequestsMvp;
import com.github.handioq.diberapp.ui.requests.RequestsPresenter;
import com.github.handioq.diberapp.ui.reviews.ReviewsMvp;
import com.github.handioq.diberapp.ui.reviews.ReviewsPresenter;
import com.github.handioq.diberapp.util.AuthPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @PresenterScope
    public LoginMvp.Presenter providesLoginPresenter(NetworkService networkService, AuthPreferences authPreferences) {
        return new LoginPresenter(networkService, authPreferences);
    }

    @Provides
    @PresenterScope
    public OrdersMvp.Presenter providesOrdersPresenter(NetworkService networkService) {
        return new OrdersPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public SignupMvp.Presenter providesSignupPresenter(NetworkService networkService) {
        return new SignupPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public AddressesMvp.Presenter providesAddressesPresenter(NetworkService networkService) {
        return new AddressesPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public NewOrderMvp.Presenter providesNewOrderPresenter(NetworkService networkService) {
        return new NewOrderPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public OrderMvp.Presenter providesOrderPresenter(NetworkService networkService) {
        return new OrderPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public NewAddressMvp.Presenter providesNewAddressPresenter(NetworkService networkService) {
        return new NewAddressPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public RemoveAddressMvp.Presenter providesRemoveAddressPresenter(NetworkService networkService) {
        return new RemoveAddressPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public RequestsMvp.Presenter providesRequestsPresenter(NetworkService networkService) {
        return new RequestsPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public RequestMvp.Presenter providesRequestPresenter(NetworkService networkService) {
        return new RequestPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public ReviewsMvp.Presenter providesReviewsPresenter(NetworkService networkService) {
        return new ReviewsPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public RemoveOrderMvp.Presenter providesRemoveOrderPresenter(NetworkService networkService) {
        return new RemoveOrderPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public AcceptRequestMvp.Presenter providesAcceptRequestPresenter(NetworkService networkService) {
        return new AcceptRequestPresenter(networkService);
    }

}