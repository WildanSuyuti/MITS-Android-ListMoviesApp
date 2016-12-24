package com.mits.kakaroto.listmovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        TextView showTitle = (TextView) findViewById(R.id.tv_showTitle);
        TextView showGenre = (TextView) findViewById(R.id.tv_showGenre);
        TextView showYear = (TextView) findViewById(R.id.tv_showYear);
        TextView showCountry = (TextView) findViewById(R.id.tv_showCountry);
        TextView showDuration = (TextView) findViewById(R.id.tv_showDuration);
        ImageView showImage = (ImageView) findViewById(R.id.img_showImage);

        Movie movie = getIntent().getParcelableExtra("movie");
        showTitle.setText(movie.getTitle());
        showGenre.setText(movie.getGenre());
        showYear.setText(movie.getYear());
        showCountry.setText(movie.getCountry());
        showDuration.setText(movie.getDuration());
        showImage.setImageResource(movie.getImageAddrees());

    }

    public void submitRemove(View view){
        Intent returnIntent = new Intent();
        setResult(MainActivity.RESULT_REMOVE, returnIntent);
        finish();
    }

    public void submitCancel(View view) {
        finish();
    }

}
