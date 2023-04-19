package com.example.movieapplication.activity;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.movieapplication.R;
import androidx.appcompat.app.ActionBarDrawerToggle;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.model.comment.Comment;
import com.example.movieapplication.model.movie.Movie;
import com.example.movieapplication.model.movie.MovieData;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    TextView judul,sinopsis,sutradara,rilis,rating,durasi,usia,genre,pemain,jmlComment;
    LinearLayout comment;
    ImageView poster;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.DetailTollbar);
        setSupportActionBar(toolbar);

        judul = findViewById(R.id.tvJudulDetail);
        poster = findViewById(R.id.ivDetail);
        sinopsis = findViewById(R.id.tvSinopsisDetail);
        sutradara = findViewById(R.id.tvSutradaraDetail);
        rilis = findViewById(R.id.tvRilisDetail);
        rating = findViewById(R.id.tvRatingDetail);
        durasi = findViewById(R.id.tvDurasiDetail);
        usia = findViewById(R.id.tvUsiaDetail);
        genre = findViewById(R.id.tvGenreDetail);
        pemain = findViewById(R.id.tvPemainDetail);
        comment = findViewById(R.id.tvComment);
        jmlComment = findViewById(R.id.tvJmlComment);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Bundle bundle = getIntent().getBundleExtra("DataDetail");

        String idMovie = bundle.getString("id");
        getJmlComment(idMovie);

        judul.setText(bundle.getString("judul"));
        String urlImage = "https://androidmovieapplication.000webhostapp.com/" + bundle.getString("poster");
        Glide.with(DetailActivity.this)
                .load(urlImage)
                .into(poster);
        sinopsis.setText(bundle.getString("sinopsis"));
        sutradara.setText(bundle.getString("sutradara"));
        rilis.setText(bundle.getString("rilis"));
        rating.setText(bundle.getString("rating"));
        durasi.setText(bundle.getString("durasi"));
        usia.setText(bundle.getString("usia"));
        genre.setText(bundle.getString("genre"));
        pemain.setText(bundle.getString("pemain"));


        comment.setOnClickListener(v -> {
            Bundle bundleKirim = new Bundle();
            bundleKirim.putString("idMovie",bundle.getString("id"));
            Intent MasukComment = new Intent(DetailActivity.this,CommentActivity.class);
            MasukComment.putExtra("DataId",bundleKirim);
            startActivity(MasukComment);
        });
    }

    void getJmlComment(String idMovie){
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Comment> jmlRequest = apiInterface.jumlahCommentRequest(idMovie);
            jmlRequest.enqueue(new Callback<Comment>() {
                @Override
                public void onResponse(Call<Comment> call, Response<Comment> response) {
                    Boolean status = response.body().isStatus();
                    if (status){
                        String JML = response.body().getData().get(0).getJumlahComment();
                        jmlComment.setText(JML);
                    } else {
                        jmlComment.setText(0);
                    }
                }

                @Override
                public void onFailure(Call<Comment> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}