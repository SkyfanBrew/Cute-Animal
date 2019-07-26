package com.tiutiunnyk.cuteanimals.presentation.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;
import com.tiutiunnyk.cuteanimals.domain.usecases.GetAnimalItemByIdUseCase;


public class AnimalDetailActivityViewModel extends ViewModel {

    private GetAnimalItemByIdUseCase getAnimalItemByIdUseCase;

    public AnimalDetailActivityViewModel(GetAnimalItemByIdUseCase getAnimalItemByIdUseCase) {
        this.getAnimalItemByIdUseCase = getAnimalItemByIdUseCase;
    }


    public LiveData<AnimalEntity> getAnimalByIdLiveData(long animalId) {
        return getAnimalItemByIdUseCase.getAnimalByIdLiveData(animalId);
    }
}
