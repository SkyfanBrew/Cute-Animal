package com.tiutiunnyk.cuteanimals.domain.injections;

import android.support.v4.app.FragmentManager;

import com.tiutiunnyk.cuteanimals.presentation.activities.AnimalsMainActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class FragmentManagersModule {

    @Provides
    FragmentManager provideMainChatFragmentManager(AnimalsMainActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
