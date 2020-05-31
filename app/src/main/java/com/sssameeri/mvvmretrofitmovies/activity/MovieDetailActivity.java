package com.sssameeri.mvvmretrofitmovies.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sssameeri.mvvmretrofitmovies.R;
import com.sssameeri.mvvmretrofitmovies.adapter.MovieAdapter;
import com.sssameeri.mvvmretrofitmovies.databinding.ActivityMovieDetailBinding;
import com.sssameeri.mvvmretrofitmovies.model.Result;

public class MovieDetailActivity extends AppCompatActivity  {

    private Result result;
    private ActivityMovieDetailBinding activityMovieDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        Intent intent = getIntent();
        result = intent.getParcelableExtra("movieData");

        activityMovieDetailBinding.setResult(result);
    }
}
