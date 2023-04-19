package com.example.movieapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapplication.R;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.model.login.Login;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {
    TextInputEditText email,oldPassword,newPassword;
    Button btnChangePassword;
    String getEmail,getOldPassword,getNewPassword;
    Toolbar toolbar;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email = findViewById(R.id.etEmailForgetPassword);
        oldPassword = findViewById(R.id.etOldPassword);
        newPassword = findViewById(R.id.etNewPasswrod);
        btnChangePassword = findViewById(R.id.btnChangeForgetPassword);
        toolbar = findViewById(R.id.ForgetPasswordToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (getIntent().getBundleExtra("ForgetPassProfile")!=null){
            bundle = getIntent().getBundleExtra("ForgetPassProfile");
            email.setText(bundle.getString("email"));
            email.setEnabled(false);
            email.setFocusable(false);
        }

        btnChangePassword.setOnClickListener(v->{
            try {
                getEmail = email.getText().toString();
                getOldPassword = oldPassword.getText().toString();
                getNewPassword = newPassword.getText().toString();
                if (!getEmail.equals("")||!getNewPassword.equals("")||!getOldPassword.equals("")){
                    new AlertDialog.Builder(ForgetPasswordActivity.this)
                            .setTitle("Confirm")
                            .setMessage("Do you want change password?")
                            .setPositiveButton("Yes", (dialog, which) -> {
    
                                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                Call<Login> changeRequest = apiInterface.ubahPasswordRequest(getEmail,getNewPassword,getOldPassword);
                                changeRequest.enqueue(new Callback<Login>() {
                                    @Override
                                    public void onResponse(Call<Login> call, Response<Login> response) {
                                        Boolean status = response.body().isStatus();
                                        if (status){
                                            email.setText("");
                                            oldPassword.setText("");
                                            newPassword.setText("");
                                            if (getIntent().getBundleExtra("ForgetPassProfile")!=null){
                                                Toast.makeText(ForgetPasswordActivity.this, "Password berhasil diubah", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ForgetPasswordActivity.this, MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(ForgetPasswordActivity.this, "Password berhasil diubah", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        } else {
                                            Toast.makeText(ForgetPasswordActivity.this, response.body().getPesan().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
    
                                    @Override
                                    public void onFailure(Call<Login> call, Throwable t) {
                                        Toast.makeText(ForgetPasswordActivity.this, "Server gagal merespon", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(ForgetPasswordActivity.this, "Silahkan hubungi admin", Toast.LENGTH_SHORT).show();
                                    }
                                });
    
                            })
                            .setNegativeButton("No", null).show();
                } else {
                    Toast.makeText(this, "Silahkan lengkapi data", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
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