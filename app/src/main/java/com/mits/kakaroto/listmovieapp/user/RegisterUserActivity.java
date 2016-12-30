package com.mits.kakaroto.listmovieapp.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.mits.kakaroto.listmovieapp.R;
import com.mits.kakaroto.listmovieapp.database.DatabaseHandler;
import com.mits.kakaroto.listmovieapp.session.LoginActivity;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText etName, etEmail, etAddress, etPhone, etGender, etPassword;
    public static final String TAG = "TagMainActivity";

    private DatabaseHandler tblUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_user);
        Log.d(TAG, "onCreate is called");

        tblUser = new DatabaseHandler(this);

        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etAddress = (EditText) findViewById(R.id.et_address);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etGender = (EditText) findViewById(R.id.et_gender);
        etPassword = (EditText) findViewById(R.id.et_password);
    }

    public void submitSave(View view) {

        String name =  etName.getText().toString();
        String email =  etEmail.getText().toString();
        String address = etAddress.getText().toString();
        String phone =  etPhone.getText().toString();
        String gender =  etGender.getText().toString();
        String pass =  etPassword.getText().toString();

        if (email.isEmpty()){
            etEmail.setError("Email cannot be blank");
            etEmail.requestFocus();

            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Email is not valid");
            etEmail.requestFocus();

            return;
        }

        if (pass.isEmpty()){
            etPassword.setError("Password cannot be blank");
            etPassword.requestFocus();

            return;
        }

        if (pass.length() < 8){
            etPassword.setError("Password must have 8 characters.");
            etPassword.requestFocus();

            return;
        }
        tblUser.addUser(new User(name, email, address, phone, gender, pass));

        startActivity(new Intent(this, LoginActivity.class));
    }

}
