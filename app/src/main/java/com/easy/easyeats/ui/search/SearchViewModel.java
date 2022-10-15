package com.easy.easyeats.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.easy.easyeats.model.PinsResponse;
import com.easy.easyeats.repository.PinsRepository;

public class SearchViewModel extends ViewModel {
    private final PinsRepository repository;
    private final MutableLiveData<String> searchInput = new MutableLiveData<>();

    public SearchViewModel(PinsRepository pinsRepository) {
        this.repository = pinsRepository;
    }

    public void setSearchInput(String query) {
        searchInput.setValue(query);
    }

    public LiveData<PinsResponse> getSearchedPins() {
        return Transformations.switchMap(searchInput, repository::searchPins);
    }
}
