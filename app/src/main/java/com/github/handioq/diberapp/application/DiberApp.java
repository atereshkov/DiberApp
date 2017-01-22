package com.github.handioq.diberapp.application;

import android.app.Application;

import com.github.handioq.diberapp.di.component.DaggerNetComponent;
import com.github.handioq.diberapp.di.component.DaggerPresenterComponent;
import com.github.handioq.diberapp.di.component.PresenterComponent;
import com.github.handioq.diberapp.di.component.NetComponent;
import com.github.handioq.diberapp.di.module.AppModule;
import com.github.handioq.diberapp.di.module.NetModule;

public class DiberApp extends Application {

    private NetComponent netComponent;
    private PresenterComponent presenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(this))
                .build();

        presenterComponent = DaggerPresenterComponent.builder()
                .netComponent(netComponent)
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public void setNetComponent(NetComponent netComponent) {
        this.netComponent = netComponent;
    }

    public PresenterComponent getPresenterComponent() {
        return presenterComponent;
    }
}