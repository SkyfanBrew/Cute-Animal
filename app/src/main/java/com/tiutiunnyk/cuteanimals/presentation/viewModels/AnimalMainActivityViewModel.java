package com.tiutiunnyk.cuteanimals.presentation.viewModels;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Parcelable;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;
import com.tiutiunnyk.cuteanimals.domain.usecases.GetListOfCatsLiveDataUseCase;
import com.tiutiunnyk.cuteanimals.domain.usecases.GetListOfDogsLiveDataUseCase;
import com.tiutiunnyk.cuteanimals.domain.usecases.InitListOfCatsDataUseCase;
import com.tiutiunnyk.cuteanimals.domain.usecases.InitListOfDogsDataUseCase;
import com.tiutiunnyk.cuteanimals.presentation.fragments.BaseAnimalFragment;

import java.util.List;


public class AnimalMainActivityViewModel extends ViewModel {

    private GetListOfCatsLiveDataUseCase getListOfCatsLiveDataUseCase;
    private GetListOfDogsLiveDataUseCase getListOfDogsLiveDataUseCase;
    private InitListOfCatsDataUseCase initListOfCatsDataUseCase;
    private InitListOfDogsDataUseCase initListOfDogsDataUseCase;
    private Parcelable currentScrollState;
    private Parcelable previousScrollState;
    private BaseAnimalFragment currentFragment;

    public AnimalMainActivityViewModel(GetListOfCatsLiveDataUseCase getListOfCatsLiveDataUseCase,
                                       GetListOfDogsLiveDataUseCase getListOfDogsLiveDataUseCase,
                                       InitListOfCatsDataUseCase initListOfCatsDataUseCase,
                                       InitListOfDogsDataUseCase initListOfDogsDataUseCase) {
        this.getListOfCatsLiveDataUseCase = getListOfCatsLiveDataUseCase;
        this.getListOfDogsLiveDataUseCase = getListOfDogsLiveDataUseCase;
        this.initListOfCatsDataUseCase = initListOfCatsDataUseCase;
        this.initListOfDogsDataUseCase = initListOfDogsDataUseCase;
    }

    public void initAnimalsListsData() {
        initCatsData();
        initDogsData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void initCatsData() {
        initListOfCatsDataUseCase.initCatsData();
    }

    public void initDogsData() {
        initListOfDogsDataUseCase.initDogsData();
    }


    public LiveData<List<AnimalEntity>> getListOfCatsLiveData() {
        return getListOfCatsLiveDataUseCase.getListOfCatsLiveData();
    }

    public LiveData<List<AnimalEntity>> getListOfDogsLiveData() {
        return getListOfDogsLiveDataUseCase.getListOfDogsLiveData();
    }

    public void saveCurrentScrollState(Parcelable onSaveInstanceState) {
        currentScrollState = onSaveInstanceState;
    }

    public Parcelable getCurrentScrollState() {
        return currentScrollState;
    }

    public void savePreviousScrollState(Parcelable layoutSavedState) {
        if (currentScrollState != null) {
            this.currentScrollState = previousScrollState;
            this.previousScrollState = layoutSavedState;
        } else {
            this.previousScrollState = layoutSavedState;
        }
    }

    public Parcelable getPreviousScrollState() {
        return previousScrollState;
    }

    public void setCurrentFragment(BaseAnimalFragment tabContentFragment) {
        this.currentFragment = tabContentFragment;
    }

    public BaseAnimalFragment getCurrentFragment() {
        return currentFragment;
    }
}
