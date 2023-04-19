package com.example.movieapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieapplication.R;
import com.example.movieapplication.api.ApiClient;
import com.example.movieapplication.api.ApiInterface;
import com.example.movieapplication.fragment.GenreFragment;
import com.example.movieapplication.fragment.GenreOptionFragment;
import com.example.movieapplication.fragment.GenreOptionInterface;
import com.example.movieapplication.fragment.HomeFragment;
import com.example.movieapplication.fragment.SearchFragment;
import com.example.movieapplication.model.login.Login;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GenreOptionInterface {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentManager fragmentManager;
    static SharedPreferences sharedPreferences;
//    private ActivityMainBinding binding;
//    AppBarConfiguration mAppBarConfiguration;
    TextView name,email,HeaderTitle;
    ImageView ivProfile;
    RelativeLayout HeaderLayout;
    String userid,username,useremail,userimg,frg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("MovieApp", Context.MODE_PRIVATE);

        if (sharedPreferences.getString("id",null)!=null){
            getAkunData(sharedPreferences.getString("id",null));
        } else {
            Toast.makeText(this,"Anda Hecker?", Toast.LENGTH_SHORT).show();
        }

        frg = "Fragment";

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nvBar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.dlMain);

        View headerView = navigationView.getHeaderView(0);
        email = headerView.findViewById(R.id.tvEmailProfile);
        name = headerView.findViewById(R.id.tvNameProfile);
        HeaderTitle = findViewById(R.id.tvHeaderApp);
        ivProfile = headerView.findViewById(R.id.ivProfile);
        HeaderLayout = headerView.findViewById(R.id.HeaderProfile);

        HeaderLayout.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id_user",userid);
            bundle.putString("name_user",username);
            bundle.putString("email_user",useremail);
            bundle.putString("img_profile",userimg);
            Intent profile = new Intent(MainActivity.this,ProfileActivity.class);
            profile.putExtra("idProfile",bundle);
            startActivity(profile);
        });

        fragmentManager = getSupportFragmentManager();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        return true;
                    }
        });

        fragmentManager.beginTransaction().replace(R.id.navContainer,new HomeFragment()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                try {
                    int id = item.getItemId();
                    switch (id){
                        case R.id.nbHome:
                            FragmentTransaction HomeFragment = getSupportFragmentManager().beginTransaction();
                            HomeFragment.replace(R.id.navContainer, new HomeFragment());
                            HomeFragment.commit();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            HeaderTitle.setText("MOVIE");
                            break;
                        case R.id.nbSearch:
                            FragmentTransaction SearchTransaction = getSupportFragmentManager().beginTransaction();
                            SearchTransaction.replace(R.id.navContainer, new SearchFragment());
                            SearchTransaction.addToBackStack(frg);
                            SearchTransaction.commit();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            HeaderTitle.setText("MOVIE");
                            break;
                        case R.id.nbLogout:
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Konfirmasi")
                                    .setMessage("kamu yakin ingin logout?")
                                    .setPositiveButton("Ya", (dialog, which) -> {
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.clear().apply();
                                        Intent KembaliLogin = new Intent(MainActivity.this,LoginActivity.class);
                                        startActivity(KembaliLogin);
                                        finish();
                                    })
                                    .setNegativeButton("Tidak", ((dialogInterface, i) -> {
                                        navigationView.setCheckedItem(R.id.nbHome);
                                        onBackPressed();
                                    })).show();
                            break;
                        case R.id.nbGenre:
                            FragmentTransaction GenreTransaction = getSupportFragmentManager().beginTransaction();
                            GenreTransaction.replace(R.id.navContainer, new GenreOptionFragment());
                            GenreTransaction.addToBackStack(frg);
                            GenreTransaction.commit();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            HeaderTitle.setText("MOVIE");
                            break;
                        case R.id.nbAbout:
                            Toast.makeText(MainActivity.this, "I Have Secret !)", Toast.LENGTH_SHORT).show();
                            onResume();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;
                    }
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                    return true;
            }
        });
        getSupportFragmentManager().popBackStack(frg,0);
    }
    void getAkunData(String id){
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Login> loginData = apiInterface.loginDataRequest(id);
            loginData.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    Boolean status = response.body().isStatus();
                    if (status!=null){
                        userid = response.body().getData().get(0).getIdUser();
                        username = response.body().getData().get(0).getName();
                        useremail = response.body().getData().get(0).getEmail();
                        userimg = response.body().getData().get(0).getImgUser();
                        email.setText(useremail);
                        name.setText(username);
                        if (response.body().getData().get(0).getImgUser()!=null){
                            String urlImage = "https://androidmovieapplication.000webhostapp.com/" + userimg;
                            Glide.with(MainActivity.this)
                                    .load(urlImage)
                                    .apply(RequestOptions.circleCropTransform())
                                    .into(ivProfile);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Gagal Memuat Data Login", Toast.LENGTH_SHORT).show();
                        HeaderLayout.setEnabled(false);
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack(frg, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            HeaderTitle.setText("MOVIE");
        } else {
            super.onBackPressed();
            HeaderTitle.setText("MOVIE");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAkunData(sharedPreferences.getString("id",null));
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static String idUser(){
        return sharedPreferences.getString("id",null);
    }

    @Override
    public void setTitle(String title) {
        HeaderTitle.setText(title);
    }
}