package com.tiutiunnyk.cuteanimals.domain.injections;

import android.content.Context;

import com.tiutiunnyk.cuteanimals.data.room.AppDataBase;
import com.tiutiunnyk.cuteanimals.data.room.dao.AnimalDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    @Singleton
    @Provides
    public AppDataBase provideDatabase(Context context) {
        return AppDataBase.getInstance(context);
    }

    @Singleton
    @Provides
    public AnimalDao provideAnimalDao(AppDataBase appDataBase) {
        return appDataBase.animalDao();
    }

}
