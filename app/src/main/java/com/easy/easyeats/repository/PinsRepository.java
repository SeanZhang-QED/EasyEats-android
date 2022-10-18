package com.easy.easyeats.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.easy.easyeats.EasyEatsApplication;
import com.easy.easyeats.database.EasyEatsDatabase;
import com.easy.easyeats.model.Pin;
import com.easy.easyeats.model.PinsResponse;
import com.easy.easyeats.network.RetrofitClient;
import com.easy.easyeats.network.UnsplashApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// TODO: What is the role of Repository in MVVM
// belongs to Model level, fetch data from remote data source
public class PinsRepository {

    private final UnsplashApi api;
    private final EasyEatsDatabase database;

    public PinsRepository() {
        api = RetrofitClient.newInstance().create(UnsplashApi.class);
        database = EasyEatsApplication.getDatabase(); // the database instance is provided by casting the application context into Application.
    }

    public LiveData<List<Pin>> getTopPins() {
        MutableLiveData<List<Pin>> topPinsLiveData = new MutableLiveData<>();
        api.getTopPins("food", 30)
                .enqueue(new Callback<List<Pin>>() {
                    @Override
                    public void onResponse(Call<List<Pin>> call, Response<List<Pin>> response) {
                        if(response.isSuccessful()) {
                            topPinsLiveData.setValue(response.body());
                        } else {
                            topPinsLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Pin>> call, Throwable t) {
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

    // TODO: Why do we need to use Async Task to accessing local database? -> dispatch
    // Database query accessing the disk storage can be very slow sometimes.
    // We do not want it to run on the default main UI thread.
    // So we use an AsyncTask to dispatch the query work to a background thread.
    private static class LikedAsyncTask extends AsyncTask<Pin, Void, Boolean> {
        private final EasyEatsDatabase database;
        private final MutableLiveData<Boolean> liveData;

        public LikedAsyncTask(EasyEatsDatabase database, MutableLiveData<Boolean> liveData) {
            this.database = database;
            this.liveData = liveData;
        }

        @Override
        // Everything inside doInBackground would be executed on a separate background thread.
        protected Boolean doInBackground(Pin... pins) {
            Pin pin = pins[0];
            try {
                database.pinDao().savePin(pin);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        // After doInBackground finishes, onPostExecute would be executed back on the main UI thread.
        protected void onPostExecute(Boolean success) {
            liveData.setValue(success);
        }
    }

    public LiveData<Boolean> likePin(Pin pin) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        new LikedAsyncTask(database, resultLiveData).execute(pin); // execute returns immediately.
        // The database operation runs in the background and notifies the result through the resultLiveData at a later time.
        return resultLiveData;
    }

    public LiveData<List<Pin>> getAllLikedPins() {
        return database.pinDao().getAllPins();
    }

    public void deleteLikedPin(Pin pin) {
        // A simpler version of the AsyncTask to run deleteArticle operation.
        // It’s convenient when you don’t care about the result
        // or the intermediate progress. Those callbacks can be skipped.
        AsyncTask.execute(() -> database.pinDao().deletePin(pin));
    }
}
