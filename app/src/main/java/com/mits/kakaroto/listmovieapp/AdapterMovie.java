package com.mits.kakaroto.listmovieapp;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by kakaroto on 12/21/16.
 */

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MyViewHolder> {

    private List<Movie> dataset;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView author, year, genre, country, duration;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            author = (TextView) itemView.findViewById(R.id.tv_author);
            genre = (TextView) itemView.findViewById(R.id.tv_genre);
            year = (TextView) itemView.findViewById(R.id.tv_year);
            country = (TextView) itemView.findViewById(R.id.tv_country);
            duration = (TextView) itemView.findViewById(R.id.tv_durasi);
            image = (ImageView) itemView.findViewById(R.id.img_film);
        }
    }

    public AdapterMovie (List<Movie> dataset){
        this.dataset = dataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = dataset.get(position);

        holder.author.setText(movie.getAuthor());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        holder.country.setText(movie.getCountry());
        holder.duration.setText(movie.getDuration());
        holder.image.setImageResource(movie.getImageAddrees());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
