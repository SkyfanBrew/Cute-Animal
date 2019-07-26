package com.tiutiunnyk.cuteanimals.presentation.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.tiutiunnyk.cuteanimals.R;
import com.tiutiunnyk.cuteanimals.presentation.viewModels.AnimalDetailActivityViewModel;
import com.tiutiunnyk.cuteanimals.presentation.viewModels.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;


public class AnimalDetailActivity extends DaggerAppCompatActivity {


    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.detail_image)
    ImageView animalAvatar;
    @BindView(R.id.detail_title)
    TextView animalTitle;
    @BindView(R.id.detail_description)
    TextView animalDescription;

    public static final String ANIMAL_ITEM_ID = "ANIMAL_ITEM_ID";
    private AnimalDetailActivityViewModel viewModel;
    private long animalId;

    public static Intent createStartIntent(Context context, long itemId) {
        Intent intent = new Intent(context, AnimalDetailActivity.class);
        if (itemId > -1) {
            intent.putExtra(ANIMAL_ITEM_ID, itemId);
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AnimalDetailActivityViewModel.class);
        setContentView(R.layout.activity_animal_details);
        ButterKnife.bind(this);

        Bundle sourceBundle = savedInstanceState != null ? savedInstanceState : getIntent().getExtras();
        animalId = sourceBundle != null ? sourceBundle.getLong(ANIMAL_ITEM_ID, -1) : -1;
        setupSubscriptions(animalId);

    }

    private void setupSubscriptions(long animalId) {
        viewModel.getAnimalByIdLiveData(animalId).observe(this, animalEntity -> {
            if (animalEntity != null) {
                loadAvatar(animalEntity.getUrl());
                animalTitle.setText(animalEntity.getTitle());
            }
        });
    }

    private void loadAvatar(String url) {
        RequestOptions options = new RequestOptions()
                .transform(new RoundedCorners(6));
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(new ObjectKey(url))
                .placeholder(R.drawable.ic_launcher_background)
                .apply(options)
                .into(animalAvatar);
    }
}
