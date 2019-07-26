package com.tiutiunnyk.cuteanimals.domain.usecases;

import android.arch.lifecycle.LiveData;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;
import com.tiutiunnyk.cuteanimals.domain.repositories.AnimalRepository;

import javax.inject.Inject;


public class GetAnimalItemByIdUseCase {

    private AnimalRepository animalRepository;

    @Inject
    public GetAnimalItemByIdUseCase(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public LiveData<AnimalEntity> getAnimalByIdLiveData(long animalId) {
        return animalRepository.getAnimalByIdLiveData(animalId);
    }
}
