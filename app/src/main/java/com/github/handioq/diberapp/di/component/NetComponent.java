package com.github.handioq.diberapp.di.component;

import com.github.handioq.diberapp.di.module.AppModule;
import com.github.handioq.diberapp.di.module.NetModule;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    NetworkService networkService();

    //AuthPreferences authPreferences();

}