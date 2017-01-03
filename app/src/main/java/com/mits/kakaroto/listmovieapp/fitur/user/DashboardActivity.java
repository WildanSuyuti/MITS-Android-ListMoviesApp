package com.mits.kakaroto.listmovieapp.fitur.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mits.kakaroto.listmovieapp.R;
import com.mits.kakaroto.listmovieapp.fitur.movie.MovieActivity;
import com.mits.kakaroto.listmovieapp.fitur.auth.LoginActivity;
import com.mits.kakaroto.listmovieapp.model.User;
import com.mits.kakaroto.listmovieapp.utility.SessionManager;


public class DashboardActivity extends AppCompatActivity {
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = SessionManager.getInstance();
        setContentView(R.layout.activity_dashbord);

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tvUser = (TextView) findViewById(R.id.tv_user);

        String sessionEmail = sessionManager.getEmail();
        String sesseionPass = sessionManager.getPass();
        User user = User.getLogin(sessionEmail, sesseionPass);
        String result = " Name : " + user.getName() + "\n Email : " + user.getEmail() + "\n Address : " +
                user.getAddress() + "\n Phone : " + user.getPhone() + "\n" +
                " Gender : " + user.getGender() + "\n Password : " + user.getPassword();

        tvUser.setText(result);
    }

    public void submitLogout(View view) {
        sessionManager.clear();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void subMitShowMovie(View view) {
        startActivity(new Intent(this, MovieActivity.class));
    }

}
