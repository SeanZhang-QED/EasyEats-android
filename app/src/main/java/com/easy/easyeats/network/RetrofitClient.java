package com.easy.easyeats.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String CLIENT_ID = "Client-ID Leo-sDUL7Jzdl9KyS9_O-awHo8WsEIY196j8ILLe3Wo";
    private static final String BASE_URL = "https://api.unsplash.com/";

    public static Retrofit newInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .header("Authorization", CLIENT_ID)
                    .build();
            return chain.proceed(request);
        }
    }

}
