package com.easy.easyeats.ui.like;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.easy.easyeats.model.Pin;
import com.easy.easyeats.repository.PinsRepository;

import java.util.List;

public class LikedViewModel extends ViewModel {
    private final PinsRepository repository;

    public LikedViewModel(PinsRepository repository) {
        this.repository = repository;
    }

    // TODO: Why do we need to use LiveData?
    // Any updates in the table will immediately trigger new updates through the LiveData
    public LiveData<List<Pin>> getALlLikedPins() {
        return repository.getAllLikedPins();
    }

    public void deleteLikedPin(Pin pin) {
        repository.deleteLikedPin(pin);
    }
}
