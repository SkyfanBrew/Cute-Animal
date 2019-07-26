package com.tiutiunnyk.cuteanimals.domain.repositories;

import android.arch.lifecycle.LiveData;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;

import java.util.List;

public interface AnimalRepository {

    LiveData<List<AnimalEntity>> getLocalCatsLiveData();

    LiveData<List<AnimalEntity>> getLocalDogsLiveData();

    LiveData<AnimalEntity> getAnimalByIdLiveData(long animalId);

    void initDogsDataFromNetworkAndSaveToDB();

    void initCatsDataFromNetworkAndSaveToDB();
}
