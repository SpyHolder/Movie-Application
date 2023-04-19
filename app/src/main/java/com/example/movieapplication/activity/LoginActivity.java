package com.example.movieapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapplication.R;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.model.login.Login;
import com.example.movieapplication.model.login.LoginData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText Email,Password;
    Button btnLogin;
    TextView Register,ForgetPassword;
    ApiInterface apiInterface;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = this.getSharedPreferences("MovieApp", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("id",null)!=null){
            Intent Login = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(Login);
            finish();
        }

        Email = findViewById(R.id.etEmail);
        Password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        Register = findViewById(R.id.tvCreatAccount);
        ForgetPassword = findViewById(R.id.tvForgetPassword);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        btnLogin.setOnClickListener(v -> {
                String EMAIL = Email.getText().toString();
                String PASSWORD = Password.getText().toString();
                if (EMAIL.equals("")&&PASSWORD.equals("")){
                    Toast.makeText(this, "Harap melengkapi Email dan Password", Toast.LENGTH_SHORT).show();
                } else if (PASSWORD.equals("")){
                    Toast.makeText(this, "Harap mengisi Password", Toast.LENGTH_SHORT).show();
                } else if (EMAIL.equals("")) {
                    Toast.makeText(this, "Harap mengisi Email", Toast.LENGTH_SHORT).show();
                } else {
                    login(EMAIL,PASSWORD);
                }
        });
        Register.setOnClickListener(v -> {
                Intent masukRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(masukRegister);
        });
        ForgetPassword.setOnClickListener(v->{
            Intent masukForget = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
            startActivity(masukForget);
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

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id",response.body().getData().get(0).getIdUser());
                        editor.apply();

                        Intent Login = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(Login);
                        Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        finish();
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