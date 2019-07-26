package com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.tiutiunnyk.cuteanimals.R;
import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;
import com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.diff.AnimalListDiffCallback;
import com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.holders.AnimalViewHolder;
import com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.listenersContracts.OnClickAnimalListener;


public class AnimalsAdapter extends BaseListAdapter<
        AnimalEntity,
        OnClickAnimalListener<AnimalEntity>,
        AnimalViewHolder
        > {

    private OnClickAnimalListener listener;

    public AnimalsAdapter(@NonNull Context context,
                          @NonNull OnClickAnimalListener<AnimalEntity> listener) {
        super(context, listener, new AnimalListDiffCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AnimalViewHolder(inflate(R.layout.item_recyclerview_animal_data, viewGroup));
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.onBind(getItem(position), listener);
    }
}
