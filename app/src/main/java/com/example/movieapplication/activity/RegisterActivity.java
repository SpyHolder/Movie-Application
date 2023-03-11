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
import com.example.movieapplication.model.register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView tvLogin;
    EditText etName,etEmail,etPassword;
    Button btnRegister;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvLogin = findViewById(R.id.tvHaveAccount);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmailRegister);
        etPassword = findViewById(R.id.etPasswordRegisterasi);
        btnRegister = findViewById(R.id.btnRegister);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NAME = etName.getText().toString();
                String EMAIL = etEmail.getText().toString();
                String PASSWORD = etPassword.getText().toString();
                String LEVEL = "user";
                Register(NAME,EMAIL,PASSWORD,LEVEL);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembaliLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(kembaliLogin);
            }
        });

    }

    private void Register(String name, String email, String password,String level) {
        try {

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Register> register = apiInterface.registerRequest(name,email,password,level);
            register.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    Boolean status = response.body().isStatus();
                    String pesan = response.body().getPesan();
                    if(status==true){
                        Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        Intent kembaliLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(kembaliLogin);
                    } else {
                        Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}