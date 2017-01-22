package com.github.handioq.diberapp.di.component;

import com.github.handioq.diberapp.di.module.LoginModule;
import com.github.handioq.diberapp.di.scope.PresenterScope;
import com.github.handioq.diberapp.ui.auth.login.LoginActivity;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

}