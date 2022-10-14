package com.easy.easyeats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.easy.easyeats.model.PinsResponse;
import com.easy.easyeats.network.RetrofitClient;
import com.easy.easyeats.network.UnsplashApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up the connect of the host view of 3 fragments and the bottom bar
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);

        UnsplashApi api = RetrofitClient.newInstance().create(UnsplashApi.class);
        api.getTopPins("food").enqueue(new Callback<PinsResponse>() {
            @Override
            public void onResponse(Call<PinsResponse> call, Response<PinsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("getTopPins", response.body().toString());
                } else {
                    Log.d("getTopPins", response.toString());
                }
            }

            @Override
            public void onFailure(Call<PinsResponse> call, Throwable t) {
                Log.d("getTopPins", t.toString());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

}
