package com.mits.kakaroto.listmovieapp.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.mits.kakaroto.listmovieapp.R;
import com.mits.kakaroto.listmovieapp.main.MainActivity;
import com.mits.kakaroto.listmovieapp.database.DatabaseHandler;
import com.mits.kakaroto.listmovieapp.user.User;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private SessionManager sessionManager;

    private DatabaseHandler tblUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = SessionManager.getInstance();
        setContentView(R.layout.activity_dashbord);
        tblUser = DatabaseHandler.getInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();
        String result = "";
        TextView tvUser = (TextView) findViewById(R.id.tv_user);

        String sessionEmail = sessionManager.getEmail();
        String sesseionPass = sessionManager.getPass();

        List<User> userList = tblUser.getAllUser();
        for (User user : userList) {
            if(user.getEmail().equals(sessionEmail) &&
                    user.getPassword().equals(sesseionPass)){
                result = " Name : " + user.getName() + "\n Email : " + user.getEmail() + "\n Address : " +
                        user.getAddress() + "\n Phone : " + user.getPhone() + "\n" +
                        " Gender : " + user.getGender() + "\n Password : " + user.getPassword();
            }
        }
        tvUser.setText(result);
    }

    public void submitLogout(View view) {
        sessionManager.clear();
        finish();
    }
    public void subMitShowMovie(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

}
