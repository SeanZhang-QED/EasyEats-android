package com.easy.easyeats.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.easy.easyeats.ui.home.HomeViewModel;
import com.easy.easyeats.ui.like.LikedViewModel;
import com.easy.easyeats.ui.search.SearchViewModel;

public class PinsViewModelFactory implements ViewModelProvider.Factory {
    private final PinsRepository repository;

    public PinsViewModelFactory(PinsRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(repository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(repository);
        } else if (modelClass.isAssignableFrom(LikedViewModel.class)) {
            return (T) new LikedViewModel(repository);
        } else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }

}
