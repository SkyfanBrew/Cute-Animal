package com.tiutiunnyk.cuteanimals.presentation.activities;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.tiutiunnyk.cuteanimals.R;
import com.tiutiunnyk.cuteanimals.presentation.fragments.BaseAnimalFragment;
import com.tiutiunnyk.cuteanimals.presentation.fragments.CatsFragment;
import com.tiutiunnyk.cuteanimals.presentation.fragments.DogsFragment;
import com.tiutiunnyk.cuteanimals.presentation.viewModels.AnimalMainActivityViewModel;
import com.tiutiunnyk.cuteanimals.presentation.viewModels.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class AnimalsMainActivity extends DaggerAppCompatActivity {

    public final static String CATS_FRAG_TAG = CatsFragment.class.getName();
    public final static String DOGS_FRAG_TAG = DogsFragment.class.getName();

    @Inject
    FragmentManager fragmentManager;
    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.tabs_container)
    TabLayout tabLayout;



    private AnimalMainActivityViewModel viewModel;
    private CatsFragment catsFragment;
    private DogsFragment dogsFragment;
    private int selectedTab = -1;
    private String SELECTED_TAB_KEY = "selected_tab_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AnimalMainActivityViewModel.class);
        setContentView(R.layout.activity_animals_navigation_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            selectedTab = savedInstanceState.getInt(SELECTED_TAB_KEY, -1);
        }

        initFragments();
        setUpFragment(savedInstanceState);

        restoreTabSelection();
        setListeners();
        viewModel.initAnimalsListsData();
    }

    private void restoreTabSelection() {
        if (selectedTab > -1) {
            tabLayout.getTabAt(selectedTab).select();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_TAB_KEY, selectedTab);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        selectedTab = savedInstanceState.getInt(SELECTED_TAB_KEY);

        super.onRestoreInstanceState(savedInstanceState);
    }

    private void setListeners() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabReselected(TabLayout.Tab tab) {
            }

            public void onTabSelected(TabLayout.Tab tab) {
                selectedTab = tab.getPosition();
                viewModel.getCurrentFragment().saveCurrentScrollState();
                getTabContent(tab.getPosition());
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }
        });
    }

    private void getTabContent(int tabIndex) {
        BaseAnimalFragment tabContentFragment = getFragmentByTabPosition(tabIndex);
        if (!tabContentFragment.isAdded()) {
            viewModel.setCurrentFragment(tabContentFragment);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.tabContainer, tabContentFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    private void setUpFragment(Bundle savedInstanceState) {
        BaseAnimalFragment currentFrag = getFragmentByTabPosition(tabLayout.getSelectedTabPosition());
        if (!currentFrag.isAdded() && savedInstanceState == null) {
            viewModel.setCurrentFragment(currentFrag);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.tabContainer,
                    currentFrag,
                    currentFrag.getClass().getName());
            transaction.commit();
        }
    }

    private void initFragments() {
        catsFragment = (CatsFragment) fragmentManager.findFragmentByTag(CATS_FRAG_TAG);
        if (catsFragment == null) {
            catsFragment = CatsFragment.newInstance();
        }
        dogsFragment = (DogsFragment) fragmentManager.findFragmentByTag(DOGS_FRAG_TAG);
        if (dogsFragment == null) {
            dogsFragment = DogsFragment.newInstance();
        }
    }

    private BaseAnimalFragment getFragmentByTabPosition(int selectedTabPosition) {
        BaseAnimalFragment currentFragment;
        switch (selectedTabPosition) {
            case 0:
                currentFragment = catsFragment;
                break;

            case 1:
                currentFragment = dogsFragment;
                break;
            default:
                currentFragment = catsFragment;
        }
        return currentFragment;
    }

}
