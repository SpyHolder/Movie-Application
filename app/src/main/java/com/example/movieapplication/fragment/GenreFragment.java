package com.example.movieapplication.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieapplication.R;
import com.example.movieapplication.adapter.SearchAdapter;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.model.movie.Movie;
import com.example.movieapplication.model.movie.MovieData;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreFragment extends Fragment {
    RecyclerView rvGenre;
    SearchAdapter adapter;
    ProgressDialog progressDialog;
    static String idMovie;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            Bundle bundle = getArguments();
            idMovie = bundle.getString("id_movie");
            if (idMovie==null){
                progressDialog.dismiss();
                return;
            }
            rvGenre = view.findViewById(R.id.rvGenre);
            rvGenre.setLayoutManager(new LinearLayoutManager(getContext()));
            getDataGenre();
        } catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }


    }
    void  getDataGenre(){
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Movie> genre = apiInterface.GenreRequest(idMovie);
            genre.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Boolean status = response.body().isStatus();
                    if (status){
                        ArrayList<MovieData> model = new ArrayList<>();
                        model = response.body().getData();

                        adapter = new SearchAdapter(getContext(),model);
                        rvGenre.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Film belum ada", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Toast.makeText(getContext(), "Gagal memuat data, maklum hostingan gratis :)", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Silahkan coba lagi nanti", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });

        } catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        NavigationView navigationView = getActivity().findViewById(R.id.nvBar);
        navigationView.setCheckedItem(R.id.nbGenre);
    }
    public static String getTitle(){
        return idMovie;
    }
}