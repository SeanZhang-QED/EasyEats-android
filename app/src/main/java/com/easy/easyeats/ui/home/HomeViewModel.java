package com.easy.easyeats.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.easy.easyeats.model.PinsResponse;
import com.easy.easyeats.repository.PinsRepository;

public class HomeViewModel extends ViewModel {

    private final PinsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(PinsRepository pinsRepository) {
        this.repository = pinsRepository;
    }

    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    public LiveData<PinsResponse> getTopPins() {
        return Transformations.switchMap(countryInput, repository::getTopPins);
    }
}
