package com.tiutiunnyk.cuteanimals.domain.usecases;

import android.arch.lifecycle.LiveData;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;
import com.tiutiunnyk.cuteanimals.domain.repositories.AnimalRepository;

import java.util.List;

import javax.inject.Inject;


public class GetListOfDogsLiveDataUseCase {

    private AnimalRepository animalRepository;

    @Inject
    public GetListOfDogsLiveDataUseCase(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public LiveData<List<AnimalEntity>> getListOfDogsLiveData() {
        return animalRepository.getLocalDogsLiveData();
    }
}
