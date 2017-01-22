package com.github.handioq.diberapp.di.module;

import com.github.handioq.diberapp.di.scope.PresenterScope;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.ui.auth.login.LoginMvp;
import com.github.handioq.diberapp.ui.auth.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @PresenterScope
    public LoginMvp.Presenter providesLoginPresenter(NetworkService networkService) {
        return new LoginPresenter(networkService);
    }
}