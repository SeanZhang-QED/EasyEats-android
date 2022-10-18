package com.easy.easyeats.network;

import com.easy.easyeats.model.Pin;
import com.easy.easyeats.model.PinsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UnsplashApi {

    @GET("/photos/random")
    Call<List<Pin>> getTopPins(
            @Query("query") String query, @Query("count") int count);

    @GET("search/photos")
    Call<PinsResponse> getSearchedPins(
            @Query("query") String query, @Query("per_page") int pageSize);
}
