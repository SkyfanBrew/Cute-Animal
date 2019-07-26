package com.tiutiunnyk.cuteanimals.domain.usecases;

import com.tiutiunnyk.cuteanimals.domain.repositories.AnimalRepository;
import com.tiutiunnyk.cuteanimals.utils.PreferenceUtils;

import javax.inject.Inject;


public class InitListOfCatsDataUseCase {

    private AnimalRepository animalRepository;
    private PreferenceUtils preferenceUtils;

    @Inject
    public InitListOfCatsDataUseCase(AnimalRepository animalRepository,
                                     PreferenceUtils preferenceUtils) {
        this.animalRepository = animalRepository;
        this.preferenceUtils = preferenceUtils;
    }

    public void initCatsData() {
        if (!preferenceUtils.isCatsLoaded()) {
            animalRepository.initCatsDataFromNetworkAndSaveToDB();
        }
    }
}
