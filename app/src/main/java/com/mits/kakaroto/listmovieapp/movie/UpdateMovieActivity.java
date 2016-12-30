package com.mits.kakaroto.listmovieapp.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mits.kakaroto.listmovieapp.R;
import com.mits.kakaroto.listmovieapp.database.DatabaseHandler;
import com.mits.kakaroto.listmovieapp.main.MainActivity;


public class UpdateMovieActivity extends AppCompatActivity {
    private EditText etTitle, etGenre, etYear, etCountry, etDuration;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);

        etTitle = (EditText) findViewById(R.id.et_titleUpdate);
        etGenre = (EditText) findViewById(R.id.et_genreUpdate);
        etYear = (EditText) findViewById(R.id.et_yearUpdate);
        etCountry = (EditText) findViewById(R.id.et_countryUpdate);
        etDuration = (EditText) findViewById(R.id.et_durationUpdate);

        Movie movie = getIntent().getParcelableExtra("movie");

        id = movie.getId();
        etTitle.setText(movie.getTitle());
        etGenre.setText(movie.getGenre());
        etYear.setText(movie.getYear());
        etCountry.setText(movie.getCountry());
        etDuration.setText(movie.getDuration());

    }

    public void submitUpdate(View view){

        String title,genre,year,country,duration;
        int image = R.drawable.anime_dragon_ball_resurrection_f;

        title = etTitle.getText().toString();
        genre = etGenre.getText().toString();
        year = etYear.getText().toString();
        country = etCountry.getText().toString();
        duration = etDuration.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("data_update", new Movie(id, title,genre,year,country,duration,image));
        setResult(MainActivity.RESULT_UPDATE, returnIntent);
        finish();
    }

    public void submitCancel(View view) {
        finish();
    }

}
