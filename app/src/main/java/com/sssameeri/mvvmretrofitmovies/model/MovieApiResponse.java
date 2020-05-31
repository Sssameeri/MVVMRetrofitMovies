package com.sssameeri.mvvmretrofitmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieApiResponse implements Parcelable {
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<>();

    public final static Parcelable.Creator<MovieApiResponse> CREATOR = new Creator<MovieApiResponse>() {
        public MovieApiResponse createFromParcel(Parcel in) {
            return new MovieApiResponse(in);
        }
        public MovieApiResponse[] newArray(int size) {
            return (new MovieApiResponse[size]);
        }
    };

    protected MovieApiResponse(Parcel in) {
        in.readList(this.results, (com.sssameeri.mvvmretrofitmovies.model.Result.class.getClassLoader()));
    }

    public MovieApiResponse() {
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }
}
