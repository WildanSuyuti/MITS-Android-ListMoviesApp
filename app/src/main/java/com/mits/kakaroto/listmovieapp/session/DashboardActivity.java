package com.mits.kakaroto.listmovieapp.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.mits.kakaroto.listmovieapp.R;
import com.mits.kakaroto.listmovieapp.main.MainActivity;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private SessionManager sessionManager;

    private DbHandlerTableUsers tblUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = SessionManager.getInstance();
        setContentView(R.layout.activity_dashbord);
        tblUser = new DbHandlerTableUsers(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String result = "";
        TextView tvUser = (TextView) findViewById(R.id.tv_user);

        User user_ses = sessionManager.getUser();
        List<User> userList = tblUser.getAllUser();
        for (User user : userList) {
            if(user.getEmail().equals(user_ses.getEmail()) &&
                    user.getPassword().equals(user_ses.getPassword())){
                result = " Name : " + user.getName() + "\n Email : " + user.getEmail() + "\n Address : " +
                        user.getAddress() + "\n Phone : " + user.getPhone() + "\n" +
                        " Gender : " + user.getGender() + "\n Password : " + user.getPassword() + "\n" +
                        " \n \n \n User : "+user_ses.getEmail()+" pass : "+user_ses.getPassword();
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
