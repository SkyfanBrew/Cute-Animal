package com.tiutiunnyk.cuteanimals.domain.injections;

import com.tiutiunnyk.cuteanimals.data.repository.AnimalRepositoryImpl;
import com.tiutiunnyk.cuteanimals.data.room.dao.AnimalDao;
import com.tiutiunnyk.cuteanimals.domain.repositories.AnimalRepository;
import com.tiutiunnyk.cuteanimals.network.AnimalApi;
import com.tiutiunnyk.cuteanimals.utils.PreferenceUtils;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    AnimalRepository provideAnimalRepository(AnimalDao animalDao, AnimalApi animalApi,
                                             PreferenceUtils preferenceUtils) {
        return new AnimalRepositoryImpl(animalDao, animalApi, preferenceUtils,
                Executors.newSingleThreadExecutor());
    }
}
