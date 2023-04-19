package com.example.movieapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapplication.R;
import com.example.movieapplication.activity.DetailActivity;
import com.example.movieapplication.model.movie.MovieData;

import java.util.ArrayList;
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Context context;
    ArrayList<MovieData> data;

    public MainAdapter(Context context, ArrayList<MovieData> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        MovieData model = data.get(position);
        String poster = model.getPosterFilm();
        String urlImage = "https://androidmovieapplication.000webhostapp.com/" + poster;
        Glide.with(context)
                .load(urlImage)
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putString("id",model.getId());
                bundle.putString("poster",model.getPosterFilm());
                bundle.putString("judul",model.getNamaFilm());
                bundle.putString("sutradara",model.getSutradara());
                bundle.putString("rilis",model.getTanggalLiris());
                bundle.putString("rating",model.getRating());
                bundle.putString("durasi",model.getDurasi());
                bundle.putString("usia",model.getRatingUsia());
                bundle.putString("genre",model.getGenre());
                bundle.putString("pemain",model.getPemain());
                bundle.putString("sinopsis",model.getSinopsis());

                Intent MasukDetail = new Intent(context, DetailActivity.class);
                MasukDetail.putExtra("DataDetail",bundle);
                context.startActivity(MasukDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ivMain);
        }
    }
}