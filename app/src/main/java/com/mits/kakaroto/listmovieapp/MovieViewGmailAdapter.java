package com.mits.kakaroto.listmovieapp;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

/**
 * Created by kakaroto on 12/21/16.
 */

public class MovieViewGmailAdapter extends RecyclerView.Adapter<MovieViewGmailAdapter.MyViewHolder> {

    private List<Movie> dataset;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, year, genre, country, duration;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            genre = (TextView) itemView.findViewById(R.id.tv_genre);
            year = (TextView) itemView.findViewById(R.id.tv_year);
            country = (TextView) itemView.findViewById(R.id.tv_country);
            duration = (TextView) itemView.findViewById(R.id.tv_durasi);
            image = (ImageView) itemView.findViewById(R.id.img_film);
        }
    }

    public MovieViewGmailAdapter(List<Movie> dataset){
        this.dataset = dataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_gmail_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = dataset.get(position);

        String titile = movie.getTitle();

        holder.title.setText(titile);
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        holder.country.setText(movie.getCountry());
        holder.duration.setText(movie.getDuration());


        ColorGenerator generator = ColorGenerator.MATERIAL;
        String firstChar = String.valueOf(titile.charAt(0));
        TextDrawable drawable = TextDrawable.builder().buildRound(firstChar, generator.getRandomColor());
        holder.image.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public Movie getItem(int position) {
        return dataset.get(position);
    }

    public void insert(Movie newMovie) {
        dataset.add(0, newMovie);
        notifyItemInserted(0);
    }

     public void remove(int position){
        dataset.remove(position);
        notifyItemRemoved(position);
    }

}
