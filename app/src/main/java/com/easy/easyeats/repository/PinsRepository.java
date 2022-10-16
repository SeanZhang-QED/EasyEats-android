package com.easy.easyeats.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.easy.easyeats.model.PinsResponse;
import com.easy.easyeats.network.RetrofitClient;
import com.easy.easyeats.network.UnsplashApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// TODO: What is the role of Repository in MVVM
// belongs to Model level, fetch data from remote data source
public class PinsRepository {

    private final UnsplashApi api;

    public PinsRepository() {
        api = RetrofitClient.newInstance().create(UnsplashApi.class);
    }

    public LiveData<PinsResponse> getTopPins(String country) {
        MutableLiveData<PinsResponse> topPinsLiveData = new MutableLiveData<>();
        api.getTopPins(country.concat(" food"), 30)
                .enqueue(new Callback<PinsResponse>() {
                    @Override
                    public void onResponse(Call<PinsResponse> call, Response<PinsResponse> response) {
                        if (response.isSuccessful()) {
                            topPinsLiveData.setValue(response.body());
                        } else {
                            topPinsLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<PinsResponse> call, Throwable t) {
                        topPinsLiveData.setValue(null);
                    }
                });
        return topPinsLiveData;
    }

    public LiveData<PinsResponse> searchPins(String query) {
        MutableLiveData<PinsResponse> searchedPinsLiveData = new MutableLiveData<>();
        api.getSearchedPins(query, 50)
                .enqueue(new Callback<PinsResponse>() {
                    @Override
                    public void onResponse(Call<PinsResponse> call, Response<PinsResponse> response) {
                        if(response.isSuccessful()) {
                            searchedPinsLiveData.setValue(response.body());
                        } else {
                            searchedPinsLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<PinsResponse> call, Throwable t) {
                        searchedPinsLiveData.setValue(null);
                    }
                });
        return searchedPinsLiveData;
    }
}
