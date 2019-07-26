package com.tiutiunnyk.cuteanimals.domain.usecases;

import com.tiutiunnyk.cuteanimals.domain.repositories.AnimalRepository;
import com.tiutiunnyk.cuteanimals.utils.PreferenceUtils;

import javax.inject.Inject;


public class InitListOfDogsDataUseCase {
    private AnimalRepository animalRepository;
    private PreferenceUtils preferenceUtils;

    @Inject
    public InitListOfDogsDataUseCase(AnimalRepository animalRepository,
                                     PreferenceUtils preferenceUtils) {
        this.animalRepository = animalRepository;
        this.preferenceUtils = preferenceUtils;
    }

    public void initDogsData() {
        if (!preferenceUtils.isDogsDataLoaded()) {
            animalRepository.initDogsDataFromNetworkAndSaveToDB();
        }
    }
}
