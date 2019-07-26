package com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.diff;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;

public class AnimalListDiffCallback extends BaseDiffCallback<AnimalEntity> {


    @Override
    public boolean areItemsTheSame(@NonNull AnimalEntity oldItem, @NonNull AnimalEntity newItem) {
        return TextUtils.equals(oldItem.getUrl(), newItem.getUrl());
    }

    @Override
    public boolean areContentsTheSame(@NonNull AnimalEntity oldItem, @NonNull AnimalEntity newItem) {
        return TextUtils.equals(oldItem.getUrl(), newItem.getUrl()) &&
                TextUtils.equals(oldItem.getTitle(), newItem.getTitle());
    }
}
