package com.mits.kakaroto.listmovieapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kakaroto on 12/21/16.
 */

public class Movie implements Parcelable {
    private String title,genre,year,country,duration;
    private int imageAddrees;


    public Movie(String title, String genre, String year, String country, String duration, int imageAddres) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.country = country;
        this.duration = duration;
        this.imageAddrees = imageAddres;
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

    public int getImageAddrees() {
        return imageAddrees;
    }

    public void setImageAddrees(int imageAddrees) {
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
        dest.writeInt(this.imageAddrees);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.genre = in.readString();
        this.year = in.readString();
        this.country = in.readString();
        this.duration = in.readString();
        this.imageAddrees = in.readInt();
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


}
