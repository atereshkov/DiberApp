package com.github.handioq.diberapp.di.module;

import com.github.handioq.diberapp.di.scope.PresenterScope;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.ui.auth.login.LoginMvp;
import com.github.handioq.diberapp.ui.auth.login.LoginPresenter;
import com.github.handioq.diberapp.ui.auth.registration.SignupMvp;
import com.github.handioq.diberapp.ui.auth.registration.SignupPresenter;
import com.github.handioq.diberapp.ui.orders.OrdersMvp;
import com.github.handioq.diberapp.ui.orders.OrdersPresenter;
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
}