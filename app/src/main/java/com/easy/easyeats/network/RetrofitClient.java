package com.easy.easyeats.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// This class is responsible for providing a configured Retrofit instance,
// that can then instantiate a NewsApi implementation.
public class RetrofitClient {
    private static final String CLIENT_ID = "Client-ID Leo-sDUL7Jzdl9KyS9_O-awHo8WsEIY196j8ILLe3Wo";
    private static final String BASE_URL = "https://api.unsplash.com/";

    public static Retrofit newInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addNetworkInterceptor(new StethoInterceptor()) // add debugging tool Stetho to view network requests
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // This is to tell how a JSON response can be deserialized into model classes.
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
