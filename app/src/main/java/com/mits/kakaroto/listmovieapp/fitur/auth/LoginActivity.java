package com.mits.kakaroto.listmovieapp.fitur.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mits.kakaroto.listmovieapp.R;
import com.mits.kakaroto.listmovieapp.fitur.user.DashboardActivity;
import com.mits.kakaroto.listmovieapp.model.User;
import com.mits.kakaroto.listmovieapp.utility.SessionManager;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPass;
    private SessionManager sessionManager;

    public static final int REQUEST_REGISTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = SessionManager.getInstance();
        if (sessionManager.isLoggedin()) openDashboard();

        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.et_emailLogin);
        etPass = (EditText) findViewById(R.id.et_passwordLogin);
    }

    public void submitLogin(View view) {
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("Email cannot be blank");
            etEmail.requestFocus();

            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email is not valid");
            etEmail.requestFocus();

            return;
        }

        if (password.isEmpty()) {
            etPass.setError("Password cannot be blank");
            etPass.requestFocus();

            return;
        }

        if (password.length() < 8) {
            etPass.setError("Password must have 8 characters.");
            etPass.requestFocus();

            return;
        }

        User user = new User();
        if (user.checkUser(email, password)) {
            sessionManager.setLogin(email, password);
            openDashboard();
        } else Toast.makeText(this, "Email or password is invalid", Toast.LENGTH_SHORT).show();
    }

    public void submitSignUp(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivityForResult(intent, REQUEST_REGISTER);
    }

    private void openDashboard() {
        Intent data = new Intent(this, DashboardActivity.class);
        startActivity(data);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == REQUEST_REGISTER) {
            openDashboard();
        }
    }

}
