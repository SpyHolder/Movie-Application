package com.example.movieapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapplication.R;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.model.login.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText Email,Password;
    Button btnLogin;
    TextView Register;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.etEmail);
        Password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        Register = findViewById(R.id.tvCreatAccount);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EMAIL = Email.getText().toString();
                String PASSWORD = Password.getText().toString();
                login(EMAIL,PASSWORD);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent masukRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(masukRegister);
            }
        });

    }

    private void login(String email, String password) {

        try {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Login> login = apiInterface.loginRequest(email, password);
            login.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    Boolean status = response.body().isStatus();
                    String pesan = response.body().getPesan();
                    if(status==true){
                        Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        Intent masukMain = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(masukMain);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ec){
            Toast.makeText(this, ec.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}