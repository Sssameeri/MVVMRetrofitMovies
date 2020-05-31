package com.sssameeri.mvvmretrofitmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sssameeri.mvvmretrofitmovies.R;
import com.sssameeri.mvvmretrofitmovies.databinding.MovieItemBinding;
import com.sssameeri.mvvmretrofitmovies.model.Result;

import java.util.ArrayList;

public class MovieAdapter extends PagedListAdapter<Result,MovieAdapter.MovieViewHolder> {

    private Context context;
    private OnMovieClickListener onMovieClickListener;

    public MovieAdapter(Context context, OnMovieClickListener onMovieClickListener) {
        super(Result.callback);
        this.context = context;
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movie_item, parent, false);

        return new MovieViewHolder(movieItemBinding, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Result result = getItem(position);

        holder.movieItemBinding.setResult(result);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MovieItemBinding movieItemBinding;
        OnMovieClickListener onMovieClickListener;

        MovieViewHolder(@NonNull MovieItemBinding movieItemBinding, OnMovieClickListener onMovieClickListener) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
            this.onMovieClickListener = onMovieClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMovieClickListener.onItemClicked(getAdapterPosition());
        }
    }

    public interface OnMovieClickListener {
        void onItemClicked(int position);
    }
}

