package com.mits.kakaroto.listmovieapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mits.kakaroto.listmovieapp.movie.Movie;
import com.mits.kakaroto.listmovieapp.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunari on 30/12/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "db_movies";

    private static final String TABLE_MOVIES = "movies";
    private static final String KEY_IDMOVIES = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_YEAR = "year";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_IMAGE = "image";

    private static final String TABLE_USERS = "users";
    private static final String KEY_IDUSERS = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_PASSWORD = "password";
    public static final String TAG = "Tag Database : ";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_IDMOVIES + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_GENRE + " TEXT,"
                + KEY_YEAR + " TEXT,"
                + KEY_COUNTRY + " TEXT,"
                + KEY_DURATION + " TEXT,"
                + KEY_IMAGE + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_MOVIES_TABLE);


        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_IDUSERS + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_GENDER + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
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

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_ADDRESS, user.getAddress());
        values.put(KEY_PHONE, user.getPhone());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_PASSWORD, user.getPassword());

        db.insert(TABLE_USERS, null, values);
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

    public List<User> getAllUser() {
        List<User> userList = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setAddress(cursor.getString(3));
                user.setPhone(cursor.getString(4));
                user.setGender(cursor.getString(5));
                user.setPassword(cursor.getString(6));

                userList.add(user);
            } while (cursor.moveToNext());
        }

        db.close();
        return userList;
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

        db.update(TABLE_MOVIES, values, KEY_IDMOVIES + " = '"+ movie.getId() +"'" , null);
        db.close();
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_ADDRESS, user.getAddress());
        values.put(KEY_PHONE, user.getPhone());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_PASSWORD, user.getPassword());
        Log.d(TAG,"Update Succes !");

        db.update(TABLE_USERS, values, KEY_IDUSERS + " = '"+ user.getId() +"'" , null);
        db.close();
    }

    public void deleteMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, KEY_IDMOVIES + " = ?",
                new String[]{String.valueOf(movie.getId())});
        Log.d(TAG,"Delete Succes !");
        db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_IDUSERS + " = ?",
                new String[]{String.valueOf(user.getId())});
        Log.d(TAG,"Delete Succes !");
        db.close();
    }

    public void deleteAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, null, null);
        db.close();
    }

    public Movie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOVIES, new String[] { KEY_IDMOVIES,
                        KEY_TITLE, KEY_GENRE, KEY_YEAR, KEY_COUNTRY, KEY_DURATION,
                        KEY_IMAGE }, KEY_IDMOVIES + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Movie movie = new Movie(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), Integer.parseInt(cursor.getString(6)));
        return movie;
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_IDUSERS,
                        KEY_NAME, KEY_EMAIL, KEY_ADDRESS, KEY_PHONE, KEY_GENDER,
                        KEY_PASSWORD }, KEY_IDUSERS + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        User user = new User(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6));
        return user;
    }

    public boolean checkUser(String email, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = '" + email
                + "' AND " + KEY_PASSWORD  + " = '" + pass +"'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor.moveToFirst();
    }
}
