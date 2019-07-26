package com.tiutiunnyk.cuteanimals.domain.injections;

import android.arch.lifecycle.ViewModel;

import com.tiutiunnyk.cuteanimals.domain.usecases.GetAnimalItemByIdUseCase;
import com.tiutiunnyk.cuteanimals.domain.usecases.GetListOfCatsLiveDataUseCase;
import com.tiutiunnyk.cuteanimals.domain.usecases.GetListOfDogsLiveDataUseCase;
import com.tiutiunnyk.cuteanimals.domain.usecases.InitListOfCatsDataUseCase;
import com.tiutiunnyk.cuteanimals.domain.usecases.InitListOfDogsDataUseCase;
import com.tiutiunnyk.cuteanimals.presentation.viewModels.AnimalDetailActivityViewModel;
import com.tiutiunnyk.cuteanimals.presentation.viewModels.AnimalMainActivityViewModel;
import com.tiutiunnyk.cuteanimals.presentation.viewModels.ViewModelFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(AnimalMainActivityViewModel.class)
    ViewModel provideAnimalMainActivityViewModel(GetListOfCatsLiveDataUseCase getListOfCatsLiveDataUseCase,
                                                 GetListOfDogsLiveDataUseCase getListOfDogsLiveDataUseCase,
                                                 InitListOfCatsDataUseCase initListOfCatsDataUseCase,
                                                 InitListOfDogsDataUseCase initListOfDogsDataUseCase) {
        return new AnimalMainActivityViewModel(getListOfCatsLiveDataUseCase,
                getListOfDogsLiveDataUseCase, initListOfCatsDataUseCase, initListOfDogsDataUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(AnimalDetailActivityViewModel.class)
    ViewModel provideAnimalFragmentsViewModel(GetAnimalItemByIdUseCase getAnimalItemByIdUseCase) {
        return new AnimalDetailActivityViewModel(getAnimalItemByIdUseCase);
    }
}
