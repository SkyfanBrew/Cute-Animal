package com.tiutiunnyk.cuteanimals.domain.injections;

import com.tiutiunnyk.cuteanimals.presentation.fragments.BaseAnimalFragment;
import com.tiutiunnyk.cuteanimals.presentation.fragments.CatsFragment;
import com.tiutiunnyk.cuteanimals.presentation.fragments.DogsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract CatsFragment contributeCatsFragment();

    @ContributesAndroidInjector
    abstract DogsFragment contributeDogsFragment();

    @ContributesAndroidInjector
    abstract BaseAnimalFragment contributeBaseAnimalFragment();
}
