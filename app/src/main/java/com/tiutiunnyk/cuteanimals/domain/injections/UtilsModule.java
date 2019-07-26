package com.tiutiunnyk.cuteanimals.domain.injections;

import android.app.Application;
import android.content.Context;

import com.tiutiunnyk.cuteanimals.utils.PreferenceUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public
class UtilsModule {
    @Singleton
    @Provides
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    public PreferenceUtils providePreferenceUtils(Context context) {
        return new PreferenceUtils(context);
    }
}
