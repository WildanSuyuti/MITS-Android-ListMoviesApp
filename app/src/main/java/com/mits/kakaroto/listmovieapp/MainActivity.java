package com.mits.kakaroto.listmovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void submitGmail(View view) {
        Intent intent = new Intent(this, ViewGmailActivity.class);
        startActivity(intent);

    }

    public void submitCardview(View view) {
        Intent intent = new Intent(this, CardViewActivity.class);
        startActivity(intent);
    }

    public void submitSwipe(View view) {
        Intent intent = new Intent(this, SwipeRemoveActivity.class);
        startActivity(intent);
    }

    public void submitHorizontal(View view) {
        Intent intent = new Intent(this, HorizontalActivity.class);
        startActivity(intent);

    }

    public void submitGrid(View view) {
        Intent intent = new Intent(this, GridViewActivity.class);
        startActivity(intent);

    }

}
