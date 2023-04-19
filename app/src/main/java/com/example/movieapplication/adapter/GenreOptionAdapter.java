package com.example.movieapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;

import java.util.jar.Attributes;

public class GenreOptionAdapter extends BaseAdapter {

    Context context;
    String[] genre;
    LayoutInflater inflater;

    public GenreOptionAdapter(Context context, String[] genre) {
        this.context = context;
        this.genre = genre;
    }

    @Override
    public int getCount() {
        return genre.length;
    }

    @Override
    public Object getItem(int i) {
        return genre[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try{
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.genre_listitem, viewGroup, false);
            }

            if (view!=null) {
                TextView NameGenre = view.findViewById(R.id.tvNameGenre);
                NameGenre.setText(genre[i]);
            }
        } catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}
