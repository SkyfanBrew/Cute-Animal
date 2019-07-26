package com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.diff;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public abstract class BaseDiffCallback<T> extends DiffUtil.ItemCallback<T> {

    @Override
    public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return false;
    }
}