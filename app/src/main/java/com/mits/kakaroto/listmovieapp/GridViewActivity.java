package com.mits.kakaroto.listmovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD:app/src/main/java/com/mits/kakaroto/listmovieapp/MainActivity.java
import android.support.v7.widget.LinearLayoutManager;
=======
import android.support.v7.widget.GridLayoutManager;
>>>>>>> gridview:app/src/main/java/com/mits/kakaroto/listmovieapp/GridViewActivity.java
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

public class GridViewActivity extends AppCompatActivity {

<<<<<<< HEAD:app/src/main/java/com/mits/kakaroto/listmovieapp/MainActivity.java
    private Spinner spinnerMovie;
    private String choice;
=======

    private MovieGridViewAdapter adapter;
    private RecyclerView recyclerView;

    private Intent intent;
    //private final int REQUEST_CODE = 1;
    public static final int RESULT_ADD = 2;
    public static final int RESULT_REMOVE = 3;
    private int id;
>>>>>>> gridview:app/src/main/java/com/mits/kakaroto/listmovieapp/GridViewActivity.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD:app/src/main/java/com/mits/kakaroto/listmovieapp/MainActivity.java
        setContentView(R.layout.activity_main);
=======
        setContentView(R.layout.activity_grid_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initRecyclerView();

    }

    public void initRecyclerView() {
        List<Movie> list;
        list = new ArrayList<>();

        list.add(new Movie("Bounty Hunter", "Action", "2016", "China", "1:40:21", R.drawable.action_bounty_hunters));
        list.add(new Movie("Max Steel", "Action", "2016", "UK", "1:32:40", R.drawable.action_max_steel));
        list.add(new Movie("Code Of Honor", "Action", "2016", "USA", "1:46:07", R.drawable.action_code_of_honor));
>>>>>>> gridview:app/src/main/java/com/mits/kakaroto/listmovieapp/GridViewActivity.java

        spinnerMovie = (Spinner) findViewById(R.id.spiner_movie);
        initSpinner();

<<<<<<< HEAD:app/src/main/java/com/mits/kakaroto/listmovieapp/MainActivity.java
    }
=======
        list.add(new Movie("Trolls", "Comedy", "2016", "USA", "1:30:55", R.drawable.comedy_trolls));
        list.add(new Movie("Baked in Brooklyn", "Comedy", "2016", "USA", "1:40:32", R.drawable.comedy_baked_in_brooklyn));
        list.add(new Movie("At Cafe 6", "Comedy", "2016", "Taiwan", "1:43:20", R.drawable.comedy_at_cafe_6));

        adapter = new MovieGridViewAdapter(list);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(this, R.dimen.space_5));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Movie movie = adapter.getItem(position);
                        id=position;
                        intent = new Intent(GridViewActivity.this, DetailMovieActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("movie", new Movie(movie.getTitle(), movie.getGenre(),
                                movie.getYear(), movie.getCountry(), movie.getDuration(),
                                movie.getImageAddrees()));
                        startActivityForResult(intent, RESULT_REMOVE);
                    }
>>>>>>> gridview:app/src/main/java/com/mits/kakaroto/listmovieapp/GridViewActivity.java

    private void initSpinner() {
        List<String> listMovieGenre = new ArrayList<>();
        listMovieGenre.add("Like Gmail");
        listMovieGenre.add("Card View Type + Swipe to Remove");
        listMovieGenre.add("Horizontal");
        listMovieGenre.add("Grid");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                listMovieGenre);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMovie.setAdapter(adapter);

        choice = spinnerMovie.getSelectedItem().toString();

    }

    public void submitOk(View view) {
        if (choice.equals("Like Gmail")) {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
    }

}
