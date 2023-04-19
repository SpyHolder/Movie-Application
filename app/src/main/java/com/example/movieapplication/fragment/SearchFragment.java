package com.example.movieapplication.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
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

public class SearchFragment extends Fragment {
    RecyclerView recycler;
    SearchAdapter adapter;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText etSearch = view.findViewById(R.id.etSearch);
        recycler = view.findViewById(R.id.rvSearch);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        getData();
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = etSearch.getText().toString();
                if (search.equals("")){
                    getData();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String search = etSearch.getText().toString();
                if (!search.equals("")){
                    SearchFragment.this.getSearch(search);
                    return true;
                }
                return false;
            }
        });
    }
    void getData(){
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Movie> movie = apiInterface.listMovieRequest();
            movie.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Boolean status = response.body().isStatus();
                    if (!status){
                        Toast.makeText(getContext(), "Data tidak komentar", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<MovieData> model = new ArrayList<>();
                        model = response.body().getData();

                        adapter = new SearchAdapter(getContext(),model);
                        recycler.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    progressDialog.dismiss();
                }
                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });

        } catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    void getSearch(String search){
        try{
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Movie> movieSearch = apiInterface.searchRequest(search);
            movieSearch.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Boolean status = response.body().isStatus();
                    if (!status){
                        Toast.makeText(getContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<MovieData> data = new ArrayList<>();
                        data = response.body().getData();

                        adapter = new SearchAdapter(getContext(),data);
                        recycler.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
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
        navigationView.setCheckedItem(R.id.nbSearch);
    }
}