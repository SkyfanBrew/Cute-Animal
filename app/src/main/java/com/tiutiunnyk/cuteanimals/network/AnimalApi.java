package com.tiutiunnyk.cuteanimals.network;

import com.tiutiunnyk.cuteanimals.data.models.networkModels.DataEntity;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface AnimalApi {

    @GET("api.php")
    Single<DataEntity> getCatsData(@Query("query") String type);

    @GET("api.php")
    Single<DataEntity> getDogsData(@Query("query") String type);
}
