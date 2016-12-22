package com.mits.kakaroto.listmovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ResultMovieActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_movie);

        TextView resultSpinner = (TextView) findViewById(R.id.tv_resultSpinner);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        choice = getIntent().getStringExtra("choice");
        resultSpinner.setText(choice.toUpperCase());
        initRecyclerView(choice);
    }

    public void initRecyclerView(String choice){
        List<Movie> list = new ArrayList<>();

        if(choice.equals("Action")){
            list.add(new Movie("Bounty Hunter","Action","2016","China","1:40:21",R.drawable.action_bounty_hunters));
            list.add(new Movie("Max Steel","Action","2016","UK","1:32:40",R.drawable.action_max_steel));
            list.add(new Movie("Code Of Honor","Action","2016","USA","1:46:07",R.drawable.action_code_of_honor));

        }else if(choice.equals("Anime")){
            list.add(new Movie("Kuroko No Basuke","Anime","2016","Japan","1:56:51",R.drawable.anime_kuroko_no_basuke));
            list.add(new Movie("Dragon Ball","Anime","2015","Japan","1:33:57",R.drawable.anime_dragon_ball_resurrection_f));
            list.add(new Movie("Saint Seiya","Anime","2014","Japan","1:43:27",R.drawable.anime_saint_seiya));

        }else {
            list.add(new Movie("Trolls","Comedy","2016","USA","1:30:55",R.drawable.comedy_trolls));
            list.add(new Movie("Baked in Brooklyn","Comedy","2016","USA","1:40:32",R.drawable.comedy_baked_in_brooklyn));
            list.add(new Movie("At Cafe 6","Comedy","2016","Taiwan","1:43:20",R.drawable.comedy_at_cafe_6));
        }

        MovieAdapter adapterAction = new MovieAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterAction);
    }

}
