package com.mits.kakaroto.listmovieapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

/**
 * Created by kakaroto on 12/21/16.
 */
@Table(name = "Movie")
public class Movie extends Model implements Parcelable {

    @Column(name = "Title")
    private String title;

    @Column(name = "Genre")
    private String genre;

    @Column(name = "Year")
    private String year;

    @Column(name = "Country")
    private String country;

    @Column(name = "Duration")
    private String duration;

    @Column(name = "ImageAddress")
    private String imageAddrees;

    public Movie() {
        super();
    }

    public Movie(String title, String genre, String year, String country, String duration, String imageAddrees) {

        this.title = title;
        this.genre = genre;
        this.year = year;
        this.country = country;
        this.duration = duration;
        this.imageAddrees = imageAddrees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageAddrees() {
        return imageAddrees;
    }

    public void setImageAddrees(String imageAddrees) {
        this.imageAddrees = imageAddrees;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.genre);
        dest.writeString(this.year);
        dest.writeString(this.country);
        dest.writeString(this.duration);
        dest.writeString(this.imageAddrees);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.genre = in.readString();
        this.year = in.readString();
        this.country = in.readString();
        this.duration = in.readString();
        this.imageAddrees = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public static List<Movie> getAll() {
        return new Select().from(Movie.class)
                .orderBy("Id DESC")
                .execute();
    }

    public static void updateMovie(long id, Movie movie) {
        new Update(Movie.class)
                .set("title = ?, genre = ?, year = ?, country = ?, duration = ?, imageaddress = ?",
                        movie.getTitle(), movie.getGenre(), movie.getYear(), movie.getCountry(),
                        movie.getDuration(), movie.getImageAddrees())
                .where("Id = ? ", id)
                .execute();
    }
}
