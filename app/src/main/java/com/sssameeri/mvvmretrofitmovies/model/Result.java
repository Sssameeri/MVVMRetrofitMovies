package com.sssameeri.mvvmretrofitmovies.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

public class Result implements Parcelable {

    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("overview")
    @Expose
    private String overview;

    @BindingAdapter({"posterPath"})
    public static void loadImage(ImageView imageView, String url) {
        String imagePath = "https://image.tmdb.org/t/p/w500/" + url;
        Picasso.get().load(imagePath).into(imageView);
    }

    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }
        public Result[] newArray(int size) {
            return (new Result[size]);
        }
    };

    protected Result(Parcel in) {
        this.popularity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.video = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Result() {
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(popularity);
        dest.writeValue(video);
        dest.writeValue(posterPath);
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(overview);
    }

    public int describeContents() {
        return 0;
    }

    public static final DiffUtil.ItemCallback<Result> callback = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return true;
        }
    };
}
