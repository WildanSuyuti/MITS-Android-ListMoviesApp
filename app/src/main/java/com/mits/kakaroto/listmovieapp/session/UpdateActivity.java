package com.mits.kakaroto.listmovieapp.session;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.mits.kakaroto.listmovieapp.R;

public class UpdateActivity extends AppCompatActivity {
    private EditText etUpdateName, etUpdateEmail, etUpdateAddress, etUpdatePhone, etUpdateGender, etUpdatePass;
//    private SessionManager sessionManager;
    private DbHandlerTableUsers tblUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        sessionManager = SessionManager.getInstance();
        setContentView(R.layout.activity_update);

        tblUser = new DbHandlerTableUsers(this);

        etUpdateName = (EditText) findViewById(R.id.et_updateName);
        etUpdateEmail = (EditText) findViewById(R.id.et_updateEmail);
        etUpdateAddress = (EditText) findViewById(R.id.et_updateAddress);
        etUpdatePhone = (EditText) findViewById(R.id.et_updatePhone);
        etUpdateGender = (EditText) findViewById(R.id.et_updateGender);
        etUpdatePass = (EditText) findViewById(R.id.et_updatePassword);

//        User user = sessionManager.getUser();
        User user = getIntent().getParcelableExtra("data_update");

        etUpdateName.setText(user.getName());
        etUpdateEmail.setText(user.getEmail());
        etUpdateAddress.setText(user.getAddress());
        etUpdatePhone.setText(user.getPhone());
        etUpdateGender.setText(user.getGender());
        etUpdatePass.setText(user.getPassword());

    }

    public void submitSaveUpdate(View view) {
        String updateName = etUpdateName.getText().toString();
        String updateEmail = etUpdateEmail.getText().toString();
        String updateAddress = etUpdateAddress.getText().toString();
        String updatePhone = etUpdatePhone.getText().toString();
        String updateGender = etUpdateGender.getText().toString();
        String updatePass = etUpdatePass.getText().toString();

        tblUser.updateUser(new User(updateName, updateEmail, updateAddress, updatePhone,
                updateGender, updatePass));
//        sessionManager.setRegister(new User(updateName, updateEmail, updateAddress, updatePhone,
//                updateGender,updatePass));
        finish();
    }


}
