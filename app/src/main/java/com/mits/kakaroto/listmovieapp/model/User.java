package com.mits.kakaroto.listmovieapp.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.util.SQLiteUtils;

import java.util.List;

/**
 * Created by sunari on 29/12/16.
 */

@Table(name = "User")
public class User extends Model implements Parcelable {

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Password")
    private String password;

    public User() {
    }

    public User(String name, String email, String address, String phone, String gender, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.gender);
        dest.writeString(this.password);
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.phone = in.readString();
        this.gender = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public static List<User> getAll() {
        return new Select().from(User.class).execute();
    }

    public static boolean checkUser(String email, String password) {
        User user = new Select()
                .from(User.class)
                .where("Email = '"+ email+"' AND Password = '"+password+"'")
                .executeSingle();
        if (user != null) return true; else return false;
    }

    public static User getUserLogin(String email, String password){
        User user = new Select()
                .from(User.class)
                .where("Email = '"+ email+"' AND Password = '"+password+"'")
                .executeSingle();
        return user;
    }
}
