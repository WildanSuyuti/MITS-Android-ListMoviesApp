package com.mits.kakaroto.listmovieapp;

/**
 * Created by kakaroto on 12/21/16.
 */

public class Movie {
    private String author,genre,year,country,duration;
    private int imageAddrees;


    public Movie(String author, String genre, String year, String country, String duration, int imageAddres) {
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.country = country;
        this.duration = duration;
        this.imageAddrees = imageAddres;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
