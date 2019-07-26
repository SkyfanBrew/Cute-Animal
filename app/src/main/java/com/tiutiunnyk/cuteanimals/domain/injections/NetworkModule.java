package com.tiutiunnyk.cuteanimals.domain.injections;

import com.tiutiunnyk.cuteanimals.network.AnimalApi;
import com.tiutiunnyk.cuteanimals.network.RetrofitManager;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;

@Module
public class NetworkModule {
    @Provides
    @Reusable
    public AnimalApi provideAnimalApi(Retrofit retrofit) {
        return retrofit.create(AnimalApi.class);
    }

    @Provides
    @Reusable
    public RetrofitManager provideRetrofitManger() {
        return new RetrofitManager();
    }

    @Provides
    @Reusable
    public Retrofit provideRetrofitInterface(RetrofitManager retrofitManager) {
        return retrofitManager.getRetrofit();
    }

}
