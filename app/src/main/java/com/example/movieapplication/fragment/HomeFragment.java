package com.example.movieapplication.fragment;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movieapplication.R;
import com.example.movieapplication.adapter.MainAdapter;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.model.movie.Movie;
import com.example.movieapplication.model.movie.MovieData;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recycler;
    MainAdapter adapter;
    ProgressDialog progressDialog;
    RecyclerView.LayoutManager layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recycler = view.findViewById(R.id.rvMain);


        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);


        layout = new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(layout);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        getData();

    }
    void getData(){
        try{
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Movie> movie = apiInterface.listMovieRequest();
            movie.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Boolean status = response.body().isStatus();
                    if (status){
                        ArrayList<MovieData> data = new ArrayList<>();
                        data = response.body().getData();

                        adapter = new MainAdapter(getContext(),data);
                        recycler.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Tidak ada data", Toast.LENGTH_SHORT).show();
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
        navigationView.setCheckedItem(R.id.nbHome);
    }
}