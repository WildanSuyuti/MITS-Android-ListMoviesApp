package com.mits.kakaroto.listmovieapp.fitur.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.mits.kakaroto.listmovieapp.database.DatabaseHandler;
import com.mits.kakaroto.listmovieapp.R;
import com.mits.kakaroto.listmovieapp.model.Movie;
import com.mits.kakaroto.listmovieapp.utility.recycler.RecyclerTouchListener;
import com.mits.kakaroto.listmovieapp.utility.recycler.SpacesItemDecoration;

import java.util.List;

public class MovieActivity extends AppCompatActivity {

    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private Intent intent;
    public static final int RESULT_ADD = 2;
    public static final int RESULT_UPDATE = 3;
    private DatabaseHandler tblMovie;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tblMovie = DatabaseHandler.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    public void initRecyclerView() {
        List<Movie> list = tblMovie.getAllMovies();
        adapter = new MovieAdapter(MovieActivity.this, list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(this, R.dimen.space_5));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        intent = new Intent(MovieActivity.this, FormMovieActivity.class);
                        Movie movie = adapter.getItem(position);

                        intent.putExtra("movie", movie);
                        pos = position;
                        startActivityForResult(intent, RESULT_UPDATE);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleCallback);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            tblMovie.deleteMovieById(adapter.getItem(position).getId());
            adapter.remove(position);

        }
    };

    public void submitAddMovie(View view) {
        Intent intent = new Intent(this, FormMovieActivity.class);
        startActivityForResult(intent, RESULT_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_ADD) {
            Movie movie = data.getParcelableExtra("data_add");
            tblMovie.addMovies(movie);
            adapter.insert(movie);
            recyclerView.scrollToPosition(0);
        } else if (resultCode == RESULT_UPDATE) {
            Movie movie = data.getParcelableExtra("data_update");
            tblMovie.updateMovie(new Movie(movie.getId(), movie.getTitle(), movie.getGenre(),
                    movie.getYear(), movie.getCountry(), movie.getDuration(),
                    movie.getImageAddrees()));
            adapter.update(pos, movie);
            recyclerView.scrollToPosition(0);
        }
    }

}