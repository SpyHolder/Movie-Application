package com.example.movieapplication.api;

import com.example.movieapplication.model.list_movie.List_Movie;
import com.example.movieapplication.model.login.Login;
import com.example.movieapplication.model.movie.Movie;
import com.example.movieapplication.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginRequest(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerRequest(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("level") String level
    );

    @FormUrlEncoded
    @GET("list_movie.php")
    Call<List_Movie> listMovieRequest();

    @FormUrlEncoded
    @POST("input_list.php")
    Call<Movie> movieRequest(
            @Field("poster") String poster,
            @Field("nama_film") String nama_film,
            @Field("tanggal_liris") String tanggal,
            @Field("genre") String genre,
            @Field("awal_penayangan") String awal,
            @Field("akhir_penayangan") String akhir,
            @Field("harga_tiket") String harga,
            @Field("sinopsis") String sinopsis
    );

}
