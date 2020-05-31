package com.sssameeri.mvvmretrofitmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.sssameeri.mvvmretrofitmovies.model.Result;
import com.sssameeri.mvvmretrofitmovies.repository.MovieDataSource;
import com.sssameeri.mvvmretrofitmovies.repository.MovieDataSourceFactory;
import com.sssameeri.mvvmretrofitmovies.service.MovieApi;
import com.sssameeri.mvvmretrofitmovies.service.RetrofitInstance;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PagedMovieViewModel extends AndroidViewModel {

    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private LiveData<PagedList<Result>> pagedListLiveData;

    public PagedMovieViewModel(@NonNull Application application) {
        super(application);

        MovieApi movieApi = RetrofitInstance.getInstance();
        MovieDataSourceFactory factory =
                new MovieDataSourceFactory(application, movieApi);

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(3)
                .build();

        Executor executor = Executors.newCachedThreadPool();

        pagedListLiveData =
                new LivePagedListBuilder<Long, Result>(factory,
                config)
        .setFetchExecutor(executor)
        .build();
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
