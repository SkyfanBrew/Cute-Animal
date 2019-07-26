package com.tiutiunnyk.cuteanimals.presentation.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiutiunnyk.cuteanimals.R;
import com.tiutiunnyk.cuteanimals.data.models.dbModels.AnimalEntity;
import com.tiutiunnyk.cuteanimals.presentation.activities.AnimalDetailActivity;
import com.tiutiunnyk.cuteanimals.presentation.recyclerView.SavingStateRecyclerView;
import com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.AnimalsAdapter;
import com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.listenersContracts.OnClickAnimalListener;
import com.tiutiunnyk.cuteanimals.presentation.viewModels.AnimalMainActivityViewModel;
import com.tiutiunnyk.cuteanimals.presentation.viewModels.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class BaseAnimalFragment extends DaggerFragment implements OnClickAnimalListener<AnimalEntity> {

    private static final String BUNDLE_RECYCLER_LAYOUT = "current_frag_state";
    protected AnimalsAdapter adapter;
    protected AnimalMainActivityViewModel viewModel;
    @Inject
    ViewModelFactory viewModelFactory;
    @BindView(R.id.recyclerView)
    SavingStateRecyclerView recyclerView;
    private Parcelable savedRecyclerLayoutState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animals_content_list, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(AnimalMainActivityViewModel.class);
        setupSubscriptions();

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
        } else {
            savedRecyclerLayoutState = viewModel.getCurrentScrollState();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        viewModel.saveCurrentScrollState(recyclerView.getLayoutManager().onSaveInstanceState());
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
    }

    protected void setupSubscriptions() {
    }

    protected void submitList(List<AnimalEntity> animals) {
        if (!animals.isEmpty()) {
            if (recyclerView.getAdapter() == null || adapter == null) {
                setupRecyclerViewWithData(animals);
            } else {
                adapter.submitList(animals);
            }
        }
    }

    private void setupRecyclerViewWithData(List<AnimalEntity> animals) {
        adapter = new AnimalsAdapter(getContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter.submitList(animals);
        recyclerView.setLayoutSavedState(savedRecyclerLayoutState);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAnimalItemClicked(AnimalEntity item) {
        startDetailActivity(item);
    }

    private void startDetailActivity(AnimalEntity item) {
        startActivity(AnimalDetailActivity.createStartIntent(getActivity(), item.getId()));
    }

    public void saveCurrentScrollState() {
        if (recyclerView.getLayoutManager() != null) {
            viewModel.savePreviousScrollState(recyclerView.getLayoutManager().onSaveInstanceState());
        }
    }
}
