package com.tiutiunnyk.cuteanimals.domain.injections.component;


import com.tiutiunnyk.cuteanimals.CuteAnimalsApplication;
import com.tiutiunnyk.cuteanimals.data.repository.AnimalRepositoryImpl;
import com.tiutiunnyk.cuteanimals.domain.injections.ActivityModule;
import com.tiutiunnyk.cuteanimals.domain.injections.AppModule;
import com.tiutiunnyk.cuteanimals.domain.injections.DataBaseModule;
import com.tiutiunnyk.cuteanimals.domain.injections.FragmentManagersModule;
import com.tiutiunnyk.cuteanimals.domain.injections.FragmentModule;
import com.tiutiunnyk.cuteanimals.domain.injections.NetworkModule;
import com.tiutiunnyk.cuteanimals.domain.injections.RepositoryModule;
import com.tiutiunnyk.cuteanimals.domain.injections.UtilsModule;
import com.tiutiunnyk.cuteanimals.domain.injections.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {AndroidSupportInjectionModule.class, RepositoryModule.class,
                ActivityModule.class, FragmentModule.class,
                NetworkModule.class, ViewModelModule.class,
                FragmentManagersModule.class, DataBaseModule.class,
                UtilsModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<CuteAnimalsApplication> {


    void inject(CuteAnimalsApplication cuteAnimalsApplication);

    void inject(AnimalRepositoryImpl animalRepository);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(CuteAnimalsApplication application);

        AppComponent build();
    }
}
