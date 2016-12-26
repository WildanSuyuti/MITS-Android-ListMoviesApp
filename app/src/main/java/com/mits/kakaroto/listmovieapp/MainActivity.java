package com.mits.kakaroto.listmovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMovie;
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerMovie = (Spinner) findViewById(R.id.spiner_movie);
        initSpinner();

    }

    private void initSpinner(){
        List<String> listMovieGenre = new ArrayList<>();
        listMovieGenre.add("Like Gmail");
        listMovieGenre.add("Card View Type + Swipe to Remove");
        listMovieGenre.add("Horizontal");
        listMovieGenre.add("Grid");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                listMovieGenre);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMovie.setAdapter(adapter);

        choice = spinnerMovie.getAdapter().toString();

    }

    public void submitOk (View view){
        if (choice.equalsIgnoreCase("Grid")){
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
    }

}
