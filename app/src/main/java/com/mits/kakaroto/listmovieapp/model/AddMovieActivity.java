package com.mits.kakaroto.listmovieapp.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mits.kakaroto.listmovieapp.R;
import com.mits.kakaroto.listmovieapp.main.MainActivity;
import com.mits.kakaroto.listmovieapp.model.Movie;

public class AddMovieActivity extends AppCompatActivity {

    private EditText etTitle, etGenre, etYear, etCountry, etDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        etTitle = (EditText) findViewById(R.id.et_title);
        etGenre = (EditText) findViewById(R.id.et_genre);
        etYear = (EditText) findViewById(R.id.et_year);
        etCountry = (EditText) findViewById(R.id.et_country);
        etDuration = (EditText) findViewById(R.id.et_duration);

    }

    public void submitSave(View view) {
        String title,genre,year,country,duration;
        int image = R.drawable.anime_dragon_ball_resurrection_f;

        title = etTitle.getText().toString();
        genre = etGenre.getText().toString();
        year = etYear.getText().toString();
        country = etCountry.getText().toString();
        duration = etDuration.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("data_add", new Movie(title,genre,year,country,duration,image));
        setResult(MainActivity.RESULT_ADD, returnIntent);
        finish();
    }

    public void submitCancel(View view) {
        finish();
    }
}
