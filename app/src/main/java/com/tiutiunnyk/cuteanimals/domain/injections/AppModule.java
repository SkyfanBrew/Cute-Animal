package com.tiutiunnyk.cuteanimals.domain.injections;

import android.app.Application;

import com.tiutiunnyk.cuteanimals.CuteAnimalsApplication;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {
    @Binds
    @Singleton
    abstract Application provideApp(CuteAnimalsApplication app);

}
