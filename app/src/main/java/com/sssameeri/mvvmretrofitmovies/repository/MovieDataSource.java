package com.sssameeri.mvvmretrofitmovies.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.sssameeri.mvvmretrofitmovies.R;
import com.sssameeri.mvvmretrofitmovies.model.MovieApiResponse;
import com.sssameeri.mvvmretrofitmovies.model.Result;
import com.sssameeri.mvvmretrofitmovies.service.MovieApi;
import com.sssameeri.mvvmretrofitmovies.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Result> {

    private MovieApi movieApi;
    private Application application;

    public MovieDataSource(MovieApi movieApi, Application application) {
        this.movieApi = movieApi;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Result> callback) {
        movieApi = RetrofitInstance.getInstance();

        Call<MovieApiResponse> call = movieApi.getPopularMovieWithPages(application.getApplicationContext().getString(R.string.api_key),
                1);

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse = response.body();
                ArrayList<Result> resultsList = (ArrayList<Result>) movieApiResponse.getResults();
                callback.onResult(resultsList, null, (long)2);
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {
        movieApi = RetrofitInstance.getInstance();

        Call<MovieApiResponse> call = movieApi.getPopularMovieWithPages(application.getApplicationContext().getString(R.string.api_key),
                params.key);

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse = response.body();
                ArrayList<Result> resultsList = (ArrayList<Result>) movieApiResponse.getResults();
                callback.onResult(resultsList, params.key + 1);
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
    }
}
