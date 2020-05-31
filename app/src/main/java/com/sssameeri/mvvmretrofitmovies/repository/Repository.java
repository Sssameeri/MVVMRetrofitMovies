package com.sssameeri.mvvmretrofitmovies.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.sssameeri.mvvmretrofitmovies.R;
import com.sssameeri.mvvmretrofitmovies.model.MovieApiResponse;
import com.sssameeri.mvvmretrofitmovies.model.Result;
import com.sssameeri.mvvmretrofitmovies.service.MovieApi;
import com.sssameeri.mvvmretrofitmovies.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private Application application;
    private ArrayList<Result> resultArrayList;
    private MutableLiveData<List<Result>> mutableLiveData;

    public Repository(Application application) {
        this.application = application;
        resultArrayList = new ArrayList<>();
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Result>> getApiResponse() {

        MovieApi movieApi = RetrofitInstance.getInstance();

        Call<MovieApiResponse> movieApiResponseCall = movieApi.getPopularMovie(application
                .getApplicationContext()
                .getString(R.string.api_key));

        movieApiResponseCall.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse = response.body();

                if(movieApiResponse != null &&
                        movieApiResponse.getResults() != null) {
                    resultArrayList = (ArrayList<Result>) movieApiResponse.getResults();
                    mutableLiveData.setValue(resultArrayList);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
