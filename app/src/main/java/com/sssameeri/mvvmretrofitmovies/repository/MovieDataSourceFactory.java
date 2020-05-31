package com.sssameeri.mvvmretrofitmovies.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.sssameeri.mvvmretrofitmovies.service.MovieApi;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieApi movieApi;
    private MovieDataSource movieDataSource;
    private Application application;
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;

    public MovieDataSourceFactory(Application application,
                                  MovieApi movieApi) {
        this.application = application;
        this.movieApi = movieApi;
        movieDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(movieApi, application);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }


}
