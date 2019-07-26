package com.tiutiunnyk.cuteanimals.domain.injections;

import com.tiutiunnyk.cuteanimals.presentation.activities.AnimalDetailActivity;
import com.tiutiunnyk.cuteanimals.presentation.activities.AnimalsMainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = {FragmentManagersModule.class})
    abstract AnimalsMainActivity contributeAnimalsViewActivity();

    @ContributesAndroidInjector(modules = {FragmentManagersModule.class})
    abstract AnimalDetailActivity contributeAnimalDetailActivity();


}
