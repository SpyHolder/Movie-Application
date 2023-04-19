package com.example.movieapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.movieapplication.R;
import com.example.movieapplication.adapter.CommentAdapter;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.model.comment.Comment;
import com.example.movieapplication.model.comment.CommentData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {
    RecyclerView rvCommnet;
    EditText etComment;
    ImageButton btnSend;
    CommentAdapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        rvCommnet = findViewById(R.id.rvComment);
        etComment = findViewById(R.id.etComment);
        btnSend = findViewById(R.id.btnSendComment);
        toolbar = findViewById(R.id.CommentTollbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String idUser = MainActivity.idUser();
        Bundle bundle = getIntent().getBundleExtra("DataId");
        String idMovie = bundle.getString("idMovie");

        GetComment(idMovie);
        rvCommnet.setLayoutManager(new LinearLayoutManager(this));

        btnSend.setOnClickListener(v ->{
            try {
                String comment = etComment.getText().toString();
                if (comment.equals("")){
                    Toast.makeText(this, "Silahkan mengisi komentar anda", Toast.LENGTH_SHORT).show();
                } else {
                    sendButton(idUser,idMovie,comment);
                }
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void sendButton(String idUser,String idMovie,String comment){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Comment> commentCall = apiInterface.InputCommentRequest(idUser,idMovie,comment);
        commentCall.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                Boolean status = response.body().isStatus();
                if (status){
                    GetComment(idMovie);
                    rvCommnet.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
                    Toast.makeText(CommentActivity.this, "Berhasil Menambahkan Komentar", Toast.LENGTH_SHORT).show();
                    etComment.setText("");
                } else {
                    Toast.makeText(CommentActivity.this, "Gagal Menambahkan Komentar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast.makeText(CommentActivity.this, "Terdapat masalah pada server", Toast.LENGTH_SHORT).show();
                Toast.makeText(CommentActivity.this, "Silahkan coba lagi nanti", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void GetComment(String id){
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Comment> comment = apiInterface.ListComment(id);
            comment.enqueue(new Callback<Comment>() {
                @Override
                public void onResponse(Call<Comment> call, Response<Comment> response) {
                    Boolean status = response.body().isStatus();
                    if (status==true){
                        ArrayList<CommentData> data = new ArrayList<>();
                        data = response.body().getData();

                        adapter = new CommentAdapter(CommentActivity.this,data);
                        rvCommnet.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(CommentActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Comment> call, Throwable t) {
                    Toast.makeText(CommentActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
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