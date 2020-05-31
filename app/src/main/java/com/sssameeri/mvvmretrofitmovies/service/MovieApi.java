package com.sssameeri.mvvmretrofitmovies.service;

import com.sssameeri.mvvmretrofitmovies.model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie/popular")
    Call<MovieApiResponse> getPopularMovie(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieApiResponse> getPopularMovieWithPages(@Query("api_key") String apiKey,
                                                    @Query("page") long page);
}
