package com.mits.kakaroto.listmovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SwipeRemoveActivity extends AppCompatActivity {


    private MovieSwipeRemoveAdapter adapter;
    private RecyclerView recyclerView;
    private Intent intent;
    //private final int REQUEST_CODE = 1;
    public static final int RESULT_ADD = 2;
    public static final int RESULT_REMOVE = 3;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_remove);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initRecyclerView();

    }

    public void initRecyclerView() {
        List<Movie> list;
        list = new ArrayList<>();

        list.add(new Movie("Bounty Hunter", "Action", "2016", "China", "1:40:21", R.drawable.action_bounty_hunters));
        list.add(new Movie("Max Steel", "Action", "2016", "UK", "1:32:40", R.drawable.action_max_steel));
        list.add(new Movie("Code Of Honor", "Action", "2016", "USA", "1:46:07", R.drawable.action_code_of_honor));

        list.add(new Movie("Kuroko No Basuke", "Anime", "2016", "Japan", "1:56:51", R.drawable.anime_kuroko_no_basuke));
        list.add(new Movie("Dragon Ball", "Anime", "2015", "Japan", "1:33:57", R.drawable.anime_dragon_ball_resurrection_f));
        list.add(new Movie("Saint Seiya", "Anime", "2014", "Japan", "1:43:27", R.drawable.anime_saint_seiya));

        list.add(new Movie("Trolls", "Comedy", "2016", "USA", "1:30:55", R.drawable.comedy_trolls));
        list.add(new Movie("Baked in Brooklyn", "Comedy", "2016", "USA", "1:40:32", R.drawable.comedy_baked_in_brooklyn));
        list.add(new Movie("At Cafe 6", "Comedy", "2016", "Taiwan", "1:43:20", R.drawable.comedy_at_cafe_6));

        adapter = new MovieSwipeRemoveAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(this, R.dimen.space_5));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Movie movie = adapter.getItem(position);
                        id=position;
                        intent = new Intent(SwipeRemoveActivity.this, DetailMovieActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("movie", new Movie(movie.getTitle(), movie.getGenre(),
                                movie.getYear(), movie.getCountry(), movie.getDuration(),
                                movie.getImageAddrees()));
                        startActivityForResult(intent, RESULT_REMOVE);
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
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            adapter.remove(position);
        }
    };

    public void submitAddMovie(View view) {
        Intent intent = new Intent(this, AddMovieActivity.class);
        startActivityForResult(intent, RESULT_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String TAG = "";
        if (resultCode == RESULT_ADD) {
            Log.d(TAG, "Success add movie");
            Movie movie = data.getParcelableExtra("data");
            adapter.insert(movie);
            recyclerView.scrollToPosition(0);
        } else if (resultCode == RESULT_REMOVE) {
            adapter.remove(id);
            recyclerView.scrollToPosition(0);
        }
    }
}
