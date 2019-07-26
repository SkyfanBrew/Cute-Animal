package com.tiutiunnyk.cuteanimals.data.models.networkModels;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;

import java.util.List;


public class DataEntity {
    private List<AnimalEntity> data;

    private String message;

    public List<AnimalEntity> getData() {
        return data;
    }

    public void setData(List<AnimalEntity> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
