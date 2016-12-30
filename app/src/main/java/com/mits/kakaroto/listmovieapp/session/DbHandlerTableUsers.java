package com.mits.kakaroto.listmovieapp.session;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunari on 30/12/16.
 */

public class DbHandlerTableUsers extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "db_movies";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_PASSWORD = "password";
    public static final String TAG = "Tag Database : ";
    public DbHandlerTableUsers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
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

        db.update(TABLE_USERS, values, KEY_ID + " = '"+ user.getId() +"'" , null);
        db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        Log.d(TAG,"Delete Succes !");
        db.close();
    }

    public void deleteAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, null, null);
        db.close();
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                        KEY_NAME, KEY_EMAIL, KEY_ADDRESS, KEY_PHONE, KEY_GENDER,
                        KEY_PASSWORD }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        User user = new User(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6));
        return user;
    }
}
