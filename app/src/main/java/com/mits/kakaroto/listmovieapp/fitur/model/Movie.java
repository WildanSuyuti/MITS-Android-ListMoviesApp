package com.mits.kakaroto.listmovieapp.fitur.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kakaroto on 12/21/16.
 */

public class Movie implements Parcelable {
    private String title,genre,year,country,duration, imageAddrees;
    private int id;


    public Movie(String title, String genre, String year, String country, String duration, String imageAddres) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.country = country;
        this.duration = duration;
        this.imageAddrees = imageAddres;
    }

    public Movie(int id, String title, String genre, String year, String country, String duration, String imageAddrees) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.country = country;
        this.duration = duration;
        this.imageAddrees = imageAddrees;
        this.id = id;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        dest.writeInt(this.id);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.genre = in.readString();
        this.year = in.readString();
        this.country = in.readString();
        this.duration = in.readString();
        this.imageAddrees = in.readString();
        this.id = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
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
