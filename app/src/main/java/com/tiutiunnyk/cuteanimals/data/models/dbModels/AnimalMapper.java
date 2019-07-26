package com.tiutiunnyk.cuteanimals.data.models.dbModels;

import com.tiutiunnyk.cuteanimals.data.models.networkModels.DataEntity;

import java.util.ArrayList;
import java.util.List;

public class AnimalMapper {

    public AnimalMapper() {
    }

    public List<AnimalEntity> mapFromNetModelToDb(DataEntity dataEntity, String type) {

        ArrayList<AnimalEntity> mappedEntities = new ArrayList<>();
        if (dataEntity.getData() != null && !dataEntity.getData().isEmpty()) {
            for (AnimalEntity animalEntity : dataEntity.getData()) {
                animalEntity.setAnimalType(type);
                mappedEntities.add(animalEntity);
            }
        }
        return mappedEntities;
    }
}
