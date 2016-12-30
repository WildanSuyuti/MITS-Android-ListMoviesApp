package com.mits.kakaroto.listmovieapp.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.mits.kakaroto.listmovieapp.R;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPass;
    private SessionManager sessionManager;
    private DbHandlerTableUsers tblUser;
    private User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = SessionManager.getInstance();
        if (sessionManager.isLoggedin()) openDashboard();

        setContentView(R.layout.activity_login);
        tblUser = new DbHandlerTableUsers(this);
        etEmail = (EditText) findViewById(R.id.et_emailLogin);
        etPass = (EditText) findViewById(R.id.et_passwordLogin);

    }

    public void submitLogin(View view) {
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

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

        if (password.isEmpty()){
            etPass.setError("Password cannot be blank");
            etPass.requestFocus();

            return;
        }

        if (password.length() < 8){
            etPass.setError("Password must have 8 characters.");
            etPass.requestFocus();

            return;
        }


        List<User> userList = tblUser.getAllUser();
        for (int i = 0; i < userList.size(); i++) {

            String emailUser = userList.get(i).getEmail();
            String passUser = userList.get(i).getPassword();
            Log.d("Email :", emailUser+" Pass : "+passUser);

            if (email.equalsIgnoreCase(emailUser)
                    && password.equals(passUser)) {
                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                user = userList.get(i);
                sessionManager.setLogin(emailUser, passUser);
                    openDashboard();
            } else
                Toast.makeText(LoginActivity.this, "Email or password is invalid",
                        Toast.LENGTH_SHORT).show();
        }

    }

    public void submitSignUp(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void openDashboard() {
        Intent data = new Intent(this, DashboardActivity.class);
        data.putExtra("data_user", user);
        startActivity(data);
    }

}