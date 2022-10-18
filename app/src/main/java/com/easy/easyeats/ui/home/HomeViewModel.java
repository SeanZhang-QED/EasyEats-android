package com.easy.easyeats.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.easy.easyeats.model.Pin;
import com.easy.easyeats.model.PinsResponse;
import com.easy.easyeats.repository.PinsRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final PinsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(PinsRepository pinsRepository) {
        this.repository = pinsRepository;
    }

    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    public LiveData<List<Pin>> getTopPins(){
        return repository.getTopPins();
    }

    public void setLikedPinInput(Pin pin) {
        repository.likePin(pin); // observe the return value shows a toast when it successes
    }
}
