package com.mits.kakaroto.listmovieapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunari on 29/12/16.
 */

public class DbHandlerTableMovies extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_movies";
    private static final String TABLE_MOVIES = "movies";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_YEAR = "year";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_IMAGE = "image";
    public static final String TAG = "Tag Database : ";
    public DbHandlerTableMovies(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_GENRE + " TEXT,"
                + KEY_YEAR + " TEXT,"
                + KEY_COUNTRY + " TEXT,"
                + KEY_DURATION + " TEXT,"
                + KEY_IMAGE + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(sqLiteDatabase);
    }

    public void addMovies(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_GENRE, movie.getGenre());
        values.put(KEY_YEAR, movie.getYear());
        values.put(KEY_COUNTRY, movie.getCountry());
        values.put(KEY_DURATION, movie.getDuration());
        values.put(KEY_IMAGE, movie.getImageAddrees());

        db.insert(TABLE_MOVIES, null, values);
        Log.d(TAG,"Add data Succes !");
        db.close();
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getInt(0));
                movie.setTitle(cursor.getString(1));
                movie.setGenre(cursor.getString(2));
                movie.setYear(cursor.getString(3));
                movie.setCountry(cursor.getString(4));
                movie.setDuration(cursor.getString(5));
                movie.setImageAddrees(Integer.parseInt(cursor.getString(6)));
                movieList.add(movie);
            } while (cursor.moveToNext());
        }

        db.close();
        return movieList;
    }

    public void updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_GENRE, movie.getGenre());
        values.put(KEY_YEAR, movie.getYear());
        values.put(KEY_COUNTRY, movie.getCountry());
        values.put(KEY_DURATION, movie.getDuration());
        values.put(KEY_IMAGE, movie.getImageAddrees());
        Log.d(TAG,"Update Succes !");

        db.update(TABLE_MOVIES, values, KEY_ID + " = '"+ movie.getId() +"'" , null);
        db.close();
    }

    public void deleteMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, KEY_ID + " = ?",
                new String[]{String.valueOf(movie.getId())});
        Log.d(TAG,"Delete Succes !");
        db.close();
    }

    public Movie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOVIES, new String[] { KEY_ID,
                        KEY_TITLE, KEY_GENRE, KEY_YEAR, KEY_COUNTRY, KEY_DURATION,
        KEY_IMAGE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Movie movie = new Movie(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), Integer.parseInt(cursor.getString(6)));
        return movie;
    }
}
