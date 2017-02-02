package com.github.handioq.diberapp.di.component;

import com.github.handioq.diberapp.di.module.PresenterModule;
import com.github.handioq.diberapp.di.scope.PresenterScope;
import com.github.handioq.diberapp.ui.auth.login.LoginFragment;
import com.github.handioq.diberapp.ui.auth.registration.SignupFragment;
import com.github.handioq.diberapp.ui.orders.OrdersFragment;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(LoginFragment loginFragment);

    void inject(OrdersFragment ordersFragment);

    void inject(SignupFragment signupFragment);

}