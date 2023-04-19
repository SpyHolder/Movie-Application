package com.example.movieapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieapplication.R;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.model.login.Login;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;

import jp.wasabeef.blurry.Blurry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageChoose,profile,img1,img2,img3,img4,img5,img6;
    ImageView ivCorner1,ivCorner2,ivCorner3,ivCorner4,ivCorner5,ivCorner6;
    ImageView[] ImageArray;
    ConstraintLayout BottomLayout,LayoutBlur;
    Button save;
    TextInputEditText username,email;
    BottomSheetBehavior<ConstraintLayout> sheetBehavior;
    Bundle bundle;
    String idUser,nameUser,emailUser,imgUser,EditimgUser,urlImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        img1 = findViewById(R.id.ivAvatar1);
        img2 = findViewById(R.id.ivAvatar2);
        img3 = findViewById(R.id.ivAvatar3);
        img4 = findViewById(R.id.ivAvatar4);
        img5 = findViewById(R.id.ivAvatar5);
        img6 = findViewById(R.id.ivAvatar6);

        ivCorner1 = findViewById(R.id.ivCornerAvatar1);
        ivCorner2 = findViewById(R.id.ivCornerAvatar2);
        ivCorner3 = findViewById(R.id.ivCornerAvatar3);
        ivCorner4 = findViewById(R.id.ivCornerAvatar4);
        ivCorner5 = findViewById(R.id.ivCornerAvatar5);
        ivCorner6 = findViewById(R.id.ivCornerAvatar6);

        profile = findViewById(R.id.ivEditProfile);
        toolbar = findViewById(R.id.EditProfileTollbar);
        imageChoose = findViewById(R.id.ivImageChoose);
        save = findViewById(R.id.btnSaveProfile);
        username = findViewById(R.id.etUsernameEditProfile);
        email = findViewById(R.id.etEmailEditProfile);
        BottomLayout = findViewById(R.id.BottomLayout);
        LayoutBlur = findViewById(R.id.blur);

        bundle = getIntent().getBundleExtra("dataEditProfile");
        idUser = bundle.getString("id");
        nameUser = bundle.getString("name");
        emailUser = bundle.getString("email");
        imgUser = bundle.getString("img");

        sheetBehavior = BottomSheetBehavior.from(BottomLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutBlur.getForeground().setAlpha(0);
        save.setFocusable(true);

        ovalAvatar();
        getDataKiriman();
        getPilihanAvatar();

        imageChoose.setOnClickListener(v-> {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            LayoutBlur.getForeground().setAlpha(150);
            username.setEnabled(false);
            email.setEnabled(false);
        });
        save.setOnClickListener(v->{
            if (EditimgUser == null){
                EditimgUser = "0";
            }
            EditProfile();
        });
    }

    void getDataKiriman(){
        if (imgUser!=null){
            urlImage = "https://androidmovieapplication.000webhostapp.com/" + imgUser;
            Glide.with(EditProfileActivity.this)
                    .load(urlImage)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profile);
            switch (imgUser){
                case "ImageProfile/LelouchAvatar.jpg":
                    setDrawableResource(R.drawable.oval_shape,0,0,0,0,0);
                    break;
                case "ImageProfile/SaberAvatar.jpg":
                    setDrawableResource(0,R.drawable.oval_shape,0,0,0,0);
                    break;
                case "ImageProfile/SaitamaAvatar.jpg":
                    setDrawableResource(0,0,R.drawable.oval_shape,0,0,0);
                    break;
                case "ImageProfile/MikasaAvatar.jpg":
                    setDrawableResource(0,0,0,R.drawable.oval_shape,0,0);
                    break;
                case "ImageProfile/YagamiAvatar.jpg":
                    setDrawableResource(0,0,0,0,R.drawable.oval_shape,0);
                    break;
                case "ImageProfile/PenelopeAvatar.jpg":
                    setDrawableResource(0,0,0,0,0,R.drawable.oval_shape);
                    break;
            }
        }
        username.setText(nameUser);
        email.setText(emailUser);

    }

    void ovalAvatar(){
        setCircularImage(img1,R.drawable.lelouchavatar);
        setCircularImage(img2,R.drawable.saberavatar);
        setCircularImage(img3,R.drawable.saitamaavatar);
        setCircularImage(img4,R.drawable.mikasaavatar);
        setCircularImage(img5,R.drawable.yagamiavatar);
        setCircularImage(img6,R.drawable.penelopeavatar);

    }

    void getPilihanAvatar(){
        img1.setOnClickListener(v->{
            setDrawableResource(R.drawable.oval_shape,0,0,0,0,0);
            setCircularImage(profile,R.drawable.lelouchavatar);
            onBackPressed();
            EditimgUser = "1";
        });
        img2.setOnClickListener(v->{
           setDrawableResource(0,R.drawable.oval_shape,0,0,0,0);
            setCircularImage(profile,R.drawable.saberavatar);
            onBackPressed();
            EditimgUser = "2";
        });
        img3.setOnClickListener(v->{
            setDrawableResource(0,0,R.drawable.oval_shape,0,0,0);
            setCircularImage(profile,R.drawable.saitamaavatar);
            onBackPressed();
            EditimgUser = "3";
        });
        img4.setOnClickListener(v->{
            setDrawableResource(0,0,0,R.drawable.oval_shape,0,0);
            setCircularImage(profile,R.drawable.mikasaavatar);
            onBackPressed();
            EditimgUser = "4";
        });
        img5.setOnClickListener(v->{
            setDrawableResource(0,0,0,0,R.drawable.oval_shape,0);
            setCircularImage(profile,R.drawable.yagamiavatar);
            onBackPressed();
            EditimgUser = "5";
        });
        img6.setOnClickListener(v->{
            setDrawableResource(0,0,0,0,0,R.drawable.oval_shape);
            setCircularImage(profile,R.drawable.penelopeavatar);
            onBackPressed();
            EditimgUser = "6";
        });
    }

    void EditProfile(){
        try {
            String name = username.getText().toString();
            String emial = email.getText().toString();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Login> dataEdit = apiInterface.editAkunRequest(idUser,name,emial,EditimgUser);
            dataEdit.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    Boolean status = response.body().isStatus();
                    if (status==true){
                        Toast.makeText(EditProfileActivity.this, "Profil berhasil diedit", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    } else {
                        Toast.makeText(EditProfileActivity.this, "Profil gagal diedit", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(EditProfileActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


//    Fungsi-fungsi
    private <T> void setCircularImage(ImageView imageView, T resourceId) {
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(EditProfileActivity.this)
                .load(resourceId)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
    }
    void setDrawableResource(int pertama,int kedua,int ketiga,int keempat,int kelima,int keenam){
        ivCorner1.setImageResource(pertama);
        ivCorner2.setImageResource(kedua);
        ivCorner3.setImageResource(ketiga);
        ivCorner4.setImageResource(keempat);
        ivCorner5.setImageResource(kelima);
        ivCorner6.setImageResource(keenam);
    }
    @Override
    public void onBackPressed() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
            LayoutBlur.getForeground().setAlpha(0);
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            username.setEnabled(true);
            email.setEnabled(true);
        } else {
            super.onBackPressed();
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