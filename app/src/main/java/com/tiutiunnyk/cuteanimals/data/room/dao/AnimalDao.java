package com.tiutiunnyk.cuteanimals.data.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;

import java.util.List;


@Dao
public interface AnimalDao {

    @Query("SELECT * FROM animals WHERE animalType = :type")
    LiveData<List<AnimalEntity>> getAllByType(String type);


    @Query("SELECT * FROM animals WHERE id = :animalId")
    LiveData<AnimalEntity> getAnimalById(long animalId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnimals(List<AnimalEntity> animalsList);

}
