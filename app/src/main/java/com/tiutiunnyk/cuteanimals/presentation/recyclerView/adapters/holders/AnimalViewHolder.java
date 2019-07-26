package com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.holders;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.tiutiunnyk.cuteanimals.R;
import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;
import com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.listenersContracts.OnClickAnimalListener;


public class AnimalViewHolder extends BaseViewHolder<AnimalEntity, OnClickAnimalListener<AnimalEntity>> {

    private final View rootView;
    private ImageView avatar;
    private TextView title;
    private TextView description;

    public AnimalViewHolder(View itemView) {
        super(itemView);
        rootView = itemView;
        avatar = itemView.findViewById(R.id.animal_avatar);
        title = itemView.findViewById(R.id.animal_title);
        description = itemView.findViewById(R.id.animal_description);
    }

    @Override
    public void onBind(AnimalEntity item, @Nullable OnClickAnimalListener<AnimalEntity> listener) {

        if (listener != null) {
            rootView.setOnClickListener(v -> listener.onAnimalItemClicked(item));
        }

        title.setText(item.getTitle());

        RequestOptions options = new RequestOptions()
                .transform(new RoundedCorners(6));
        Glide.with(rootView.getContext())
                .load(item.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(new ObjectKey(item.getUrl()))
                .placeholder(R.drawable.ic_launcher_background)
                .apply(options)
                .into(avatar);

    }
}
