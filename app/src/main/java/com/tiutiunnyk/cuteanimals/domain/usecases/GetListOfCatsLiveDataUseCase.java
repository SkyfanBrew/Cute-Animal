package com.tiutiunnyk.cuteanimals.domain.usecases;

import android.arch.lifecycle.LiveData;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;
import com.tiutiunnyk.cuteanimals.domain.repositories.AnimalRepository;

import java.util.List;

import javax.inject.Inject;


public class GetListOfCatsLiveDataUseCase {

    private AnimalRepository animalRepository;

    @Inject
    public GetListOfCatsLiveDataUseCase(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public LiveData<List<AnimalEntity>> getListOfCatsLiveData() {
        return animalRepository.getLocalCatsLiveData();
    }
}
