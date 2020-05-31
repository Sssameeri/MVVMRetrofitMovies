package com.sssameeri.mvvmretrofitmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sssameeri.mvvmretrofitmovies.model.Result;
import com.sssameeri.mvvmretrofitmovies.repository.Repository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private Repository repository;

    public MovieViewModel(Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Result>> getMovies() {
        return repository.getApiResponse();
    }
}
