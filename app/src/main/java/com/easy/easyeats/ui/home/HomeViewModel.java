package com.easy.easyeats.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.easy.easyeats.model.Pin;
import com.easy.easyeats.model.PinsResponse;
import com.easy.easyeats.repository.PinsRepository;

public class HomeViewModel extends ViewModel {

    private final PinsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(PinsRepository pinsRepository) {
        this.repository = pinsRepository;
    }

    // TODO: why do we split the function into 2
    /*  -- Can split this function into 2 functions like below --
        public LiveData<NewsResponse> getTopHeadlines(String country) {
            return newsRepository.getTomHeadlines(country);
        }
    */

    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    public LiveData<PinsResponse> getTopPins() {
        // ui subscribes the data
        // 根据输入，可以先有生产线，等有输入时才生产
        // 好处是，countryInput数据被保留下来了，不是一次性的，保留state
        // 比如： 实际中，手机rotate后，view会被销毁，没有打回车了，所以countryInput为空，以前的输出无法保存下来 - onetime consume。
        return Transformations.switchMap(countryInput, repository::getTopPins);
    }

    public void setLikedPinInput(Pin pin) {
        repository.likePin(pin); // observe the return value shows a toast when it successes
    }
}
