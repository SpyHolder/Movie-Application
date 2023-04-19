package com.example.movieapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieapplication.R;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.model.login.Login;

import retrofit2.Call;

public class ProfileActivity extends AppCompatActivity {

    ImageView imgProfile;
    TextView HeaderUsername,ChildUsername,ChildEmail;
    Button EditProfile,ChangPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgProfile = findViewById(R.id.ivProfile);
        HeaderUsername = findViewById(R.id.tvHeaderUserName);
        ChildUsername = findViewById(R.id.tvChildUsername);
        ChildEmail = findViewById(R.id.tvChildEmail);
        EditProfile = findViewById(R.id.btnEditProfile);
        ChangPassword = findViewById(R.id.btnChangePassword);

        Bundle bundle = getIntent().getBundleExtra("idProfile");
        String id = bundle.getString("id_user");

        HeaderUsername.setText(bundle.getString("name_user"));
        ChildUsername.setText(bundle.getString("name_user"));
        ChildEmail.setText(bundle.getString("email_user"));
        if (bundle.getString("img_profile")!=null){
            String urlImage = "https://androidmovieapplication.000webhostapp.com/" + bundle.getString("img_profile");
            Glide.with(ProfileActivity.this)
                    .load(urlImage)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imgProfile);
        }

        Bundle kirimanEdit = new Bundle();
        kirimanEdit.putString("id",bundle.getString("id_user"));
        kirimanEdit.putString("name",bundle.getString("name_user"));
        kirimanEdit.putString("email",bundle.getString("email_user"));
        kirimanEdit.putString("img",bundle.getString("img_profile"));

        EditProfile.setOnClickListener( v -> {
            Intent profileEdit = new Intent(ProfileActivity.this, EditProfileActivity.class);
            profileEdit.putExtra("dataEditProfile",kirimanEdit);
            startActivity(profileEdit);
        });
        ChangPassword.setOnClickListener( v -> {
            Bundle kirimanForgetPass = new Bundle();
            kirimanForgetPass.putString("email",bundle.getString("email_user"));
            Intent passwordChange = new Intent(ProfileActivity.this,ForgetPasswordActivity.class);
            passwordChange.putExtra("ForgetPassProfile",kirimanForgetPass);
            startActivity(passwordChange);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}