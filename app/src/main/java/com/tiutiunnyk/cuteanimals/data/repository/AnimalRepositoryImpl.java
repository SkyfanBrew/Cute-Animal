package com.tiutiunnyk.cuteanimals.data.repository;

import android.arch.lifecycle.LiveData;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;
import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalMapper;
import com.tiutiunnyk.cuteanimals.data.room.dao.AnimalDao;
import com.tiutiunnyk.cuteanimals.domain.repositories.AnimalRepository;
import com.tiutiunnyk.cuteanimals.network.AnimalApi;
import com.tiutiunnyk.cuteanimals.utils.PreferenceUtils;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.tiutiunnyk.cuteanimals.data.enums.AnimalTypes.TYPE_CAT;
import static com.tiutiunnyk.cuteanimals.data.enums.AnimalTypes.TYPE_DOG;


public class AnimalRepositoryImpl implements AnimalRepository {

    private final AnimalDao animalDao;
    private final AnimalApi api;
    private PreferenceUtils preferenceUtils;
    private final Executor executor;
    private AnimalMapper animalMapper;
    private Disposable initDogsDataDisposable;
    private Disposable initCatsDataDisposable;

    @Inject
    public AnimalRepositoryImpl(AnimalDao animalDao, AnimalApi animalApi,
                                PreferenceUtils preferenceUtils, Executor executor) {
        this.animalDao = animalDao;
        this.api = animalApi;
        this.preferenceUtils = preferenceUtils;
        this.executor = executor;
        this.animalMapper = new AnimalMapper();
    }


    @Override
    public LiveData<List<AnimalEntity>> getLocalCatsLiveData() {
        return animalDao.getAllByType(TYPE_CAT.getName());
    }

    @Override
    public LiveData<List<AnimalEntity>> getLocalDogsLiveData() {
        return animalDao.getAllByType(TYPE_DOG.getName());
    }

    @Override
    public LiveData<AnimalEntity> getAnimalByIdLiveData(long animalId) {
        return animalDao.getAnimalById(animalId);
    }

    @Override
    public void initDogsDataFromNetworkAndSaveToDB() {
        initDogsDataDisposable = api.getDogsData(TYPE_DOG.getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataEntity -> {
                    preferenceUtils.setIsDogsDataLoaded(true);
                    insertAnimals(animalMapper.mapFromNetModelToDb(dataEntity, TYPE_DOG.getName()));
                    disposeDisposable(initDogsDataDisposable);
                }, error -> {
                    disposeDisposable(initDogsDataDisposable);
                });
    }

    @Override
    public void initCatsDataFromNetworkAndSaveToDB() {
        initCatsDataDisposable = api.getCatsData(TYPE_CAT.getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataEntity -> {
                    preferenceUtils.setIsCatsDataLoaded(true);
                    insertAnimals(animalMapper.mapFromNetModelToDb(dataEntity, TYPE_CAT.getName()));
                    disposeDisposable(initCatsDataDisposable);
                }, error -> {
                    disposeDisposable(initCatsDataDisposable);
                });
    }

    private void insertAnimals(List<AnimalEntity> animalEntities) {
        executor.execute(() -> {
            animalDao.insertAnimals(animalEntities);
        });
    }

    private void disposeDisposable(Disposable disposable) {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
