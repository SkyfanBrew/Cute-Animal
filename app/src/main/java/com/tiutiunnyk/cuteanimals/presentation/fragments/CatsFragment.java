package com.tiutiunnyk.cuteanimals.presentation.fragments;

public class CatsFragment extends BaseAnimalFragment {

    public static CatsFragment newInstance() {
        return new CatsFragment();
    }

    protected void setupSubscriptions() {
        viewModel.getListOfCatsLiveData().observe(this, this::submitList);
    }
}
