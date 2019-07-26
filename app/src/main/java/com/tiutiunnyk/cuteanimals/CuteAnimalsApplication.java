package com.tiutiunnyk.cuteanimals;

import com.tiutiunnyk.cuteanimals.domain.injections.component.AppComponent;
import com.tiutiunnyk.cuteanimals.domain.injections.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class CuteAnimalsApplication extends DaggerApplication {

    @Inject
    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
