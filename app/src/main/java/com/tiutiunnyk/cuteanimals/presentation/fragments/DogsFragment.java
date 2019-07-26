package com.tiutiunnyk.cuteanimals.presentation.fragments;

public class DogsFragment extends BaseAnimalFragment {

    public static DogsFragment newInstance() {
        return new DogsFragment();
    }

    protected void setupSubscriptions() {
        viewModel.getListOfDogsLiveData().observe(this, this::submitList);
    }
}
