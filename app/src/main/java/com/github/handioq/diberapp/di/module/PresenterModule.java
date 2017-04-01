package com.github.handioq.diberapp.di.module;

import com.github.handioq.diberapp.di.scope.PresenterScope;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.ui.addresses.AddressesMvp;
import com.github.handioq.diberapp.ui.addresses.AddressesPresenter;
import com.github.handioq.diberapp.ui.auth.login.LoginMvp;
import com.github.handioq.diberapp.ui.auth.login.LoginPresenter;
import com.github.handioq.diberapp.ui.auth.registration.SignupMvp;
import com.github.handioq.diberapp.ui.auth.registration.SignupPresenter;
import com.github.handioq.diberapp.ui.interaction.new_order.NewOrderMvp;
import com.github.handioq.diberapp.ui.interaction.new_order.NewOrderPresenter;
import com.github.handioq.diberapp.ui.order.OrderMvp;
import com.github.handioq.diberapp.ui.order.OrderPresenter;
import com.github.handioq.diberapp.ui.orders.OrdersMvp;
import com.github.handioq.diberapp.ui.orders.OrdersPresenter;
import com.github.handioq.diberapp.ui.shops.ShopsMvp;
import com.github.handioq.diberapp.ui.shops.ShopsPresenter;
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
    public ShopsMvp.Presenter providesShopsPresenter(NetworkService networkService) {
        return new ShopsPresenter(networkService);
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



}