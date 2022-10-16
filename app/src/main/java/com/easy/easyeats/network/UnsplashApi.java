package com.easy.easyeats.network;

import com.easy.easyeats.model.PinsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UnsplashApi {

    @GET("search/photos")
    Call<PinsResponse> getTopPins(
            @Query("query") String query, @Query("per_page") int pageSize);

    @GET("search/photos")
    Call<PinsResponse> getSearchedPins(
            @Query("query") String query, @Query("per_page") int pageSize);
}
