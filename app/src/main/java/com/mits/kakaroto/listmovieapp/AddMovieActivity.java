package com.mits.kakaroto.listmovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddMovieActivity extends AppCompatActivity {

    private EditText etAuthor, etGenre, etYear, etCountry, etDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        etAuthor = (EditText) findViewById(R.id.et_author);
        etGenre = (EditText) findViewById(R.id.et_genre);
        etYear = (EditText) findViewById(R.id.et_year);
        etCountry = (EditText) findViewById(R.id.et_country);
        etDuration = (EditText) findViewById(R.id.et_duration);

    }

    public void submitSave(View view) {
        final int RESULT_ADD=2;
        String author,genre,year,country,duration;
        int image = R.drawable.anime_dragon_ball_resurrection_f;

        author = etAuthor.getText().toString();
        genre = etGenre.getText().toString();
        year = etYear.getText().toString();
        country = etCountry.getText().toString();
        duration = etDuration.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("data", new Movie(author,genre,year,country,duration,image));
        setResult(RESULT_ADD, returnIntent);
        finish();
    }

    public void submitCancel(View view) {
        finish();
    }
}
