package com.sssameeri.mvvmretrofitmovies.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.sssameeri.mvvmretrofitmovies.R;
import com.sssameeri.mvvmretrofitmovies.adapter.MovieAdapter;
import com.sssameeri.mvvmretrofitmovies.databinding.ActivityMainBinding;
import com.sssameeri.mvvmretrofitmovies.model.Result;
import com.sssameeri.mvvmretrofitmovies.viewmodel.MovieViewModel;
import com.sssameeri.mvvmretrofitmovies.viewmodel.PagedMovieViewModel;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private PagedList<Result> resultPagedList;
    private MovieViewModel movieViewModel;
    private PagedMovieViewModel pagedMovieViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        movieViewModel = new ViewModelProvider
//                .AndroidViewModelFactory(getApplication())
//                .create(MovieViewModel.class);

        pagedMovieViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(PagedMovieViewModel.class);

        getMovies();

    }

    public void getMovies() {
//       movieViewModel.getMovies().observe(this, results -> {
//          resultArrayList = (ArrayList<Result>) results;
//          fillRecyclerView();
//       });

        pagedMovieViewModel.getPagedListLiveData().observe(this, results -> {
          resultPagedList = results;
          fillRecyclerView();
        });
    }

    private void fillRecyclerView() {
        RecyclerView recyclerView = activityMainBinding.recyclerView;
        MovieAdapter adapter = new MovieAdapter(this, (MovieAdapter.OnMovieClickListener) this);
        adapter.submitList(resultPagedList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        Result result = resultPagedList.get(position);
        intent.putExtra("movieData", result);
        startActivity(intent);
    }
}
