package com.mits.kakaroto.listmovieapp.fitur.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mits.kakaroto.listmovieapp.R;
import com.mits.kakaroto.listmovieapp.main.MainActivity;
import com.mits.kakaroto.listmovieapp.fitur.model.Movie;

import java.io.File;

import pl.aprilapps.easyphotopicker.EasyImage;

public class FormMovieActivity extends AppCompatActivity {

    private EditText etTitle, etGenre, etYear, etCountry, etDuration;
    private ImageView imgFormFilm;
    private Movie movie = null;
    private int id;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_movie);

        imgFormFilm = (ImageView) findViewById(R.id.img_formFilm);
        etTitle = (EditText) findViewById(R.id.et_title);
        etGenre = (EditText) findViewById(R.id.et_genre);
        etYear = (EditText) findViewById(R.id.et_year);
        etCountry = (EditText) findViewById(R.id.et_country);
        etDuration = (EditText) findViewById(R.id.et_duration);

        movie = getIntent().getParcelableExtra("movie");

        if (movie != null) {
            getSupportActionBar().setTitle("Update Data");
            id = movie.getId();
            etTitle.setText(movie.getTitle());
            etGenre.setText(movie.getGenre());
            etYear.setText(movie.getYear());
            etCountry.setText(movie.getCountry());
            etDuration.setText(movie.getDuration());
            Glide.with(FormMovieActivity.this).load(movie.getImageAddrees()).into(imgFormFilm);
        } else getSupportActionBar().setTitle("Add Data");
    }

    public void selectImage(View view) {
        EasyImage.openChooserWithDocuments(this, "Pilih gambar", 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                onPhotoReturned(imageFile);
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(FormMovieActivity.this);
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    private void onPhotoReturned(File imageFile) {
        if (imageFile != null) {
            Glide.with(this)
                    .load(imageFile)
                    .crossFade()
                    .centerCrop()
                    .into(imgFormFilm);
            path = imageFile.getAbsolutePath();
        }
    }

    public void submitSave(View view) {
        String title, genre, year, country, duration;

        title = etTitle.getText().toString();
        genre = etGenre.getText().toString();
        year = etYear.getText().toString();
        country = etCountry.getText().toString();
        duration = etDuration.getText().toString();

        Intent returnIntent = new Intent();
        if (movie != null) {
            returnIntent.putExtra("data_update", new Movie(id, title, genre, year, country, duration, path));
            setResult(MainActivity.RESULT_UPDATE, returnIntent);
        } else {
            returnIntent.putExtra("data_add", new Movie(title, genre, year, country, duration, path));
            setResult(MainActivity.RESULT_ADD, returnIntent);
        }
        finish();

    }

    public void submitCancel(View view) {
        finish();
    }


}
