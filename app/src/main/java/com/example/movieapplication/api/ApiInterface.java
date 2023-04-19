package com.example.movieapplication.api;

import com.example.movieapplication.model.comment.Comment;
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
    @POST("login/login.php")
    Call<Login> loginRequest(
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("login/DataLogin.php")
    Call<Login> loginDataRequest(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("login/register.php")
    Call<Register> registerRequest(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("level") String level
    );

    @FormUrlEncoded
    @POST("login/UbahPassword.php")
    Call<Login> ubahPasswordRequest(
            @Field("email") String email,
            @Field("password") String newPassword,
            @Field("oldpassword") String oldPassword
    );

    @FormUrlEncoded
    @POST("login/EditAccount.php")
    Call<Login> editAkunRequest(
            @Field("idUser") String id,
            @Field("nameUser") String name,
            @Field("emailUser") String email,
            @Field("imgUser") String img
    );

    @FormUrlEncoded
    @POST("login/delete_account.php")
    Call<Movie> deleteAkunRequest(
            @Field("email") String email
    );

    @GET("list_movie.php")
    Call<Movie> listMovieRequest();

    @FormUrlEncoded
    @POST("InputList.php")
    Call<Movie> inputMovieRequest(
            @Field("poster_film") String poster,
            @Field("judul") String judul,
            @Field("sutradara") String sutradara,
            @Field("liris") String rilis,
            @Field("rating") String rating,
            @Field("durasi") String durasi,
            @Field("usia") String usia,
            @Field("genre") String genre,
            @Field("pemain") String pemain,
            @Field("sinopsis") String sinposis
    );

    @FormUrlEncoded
    @POST("SearchMovie.php")
    Call<Movie> searchRequest(
            @Field("search") String search
    );

    @FormUrlEncoded
    @POST("DeleteMovie.php")
    Call<Movie> deleteMovieRequest(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("SearchGenre.php")
    Call<Movie> GenreRequest(
            @Field("cariGenre") String genre
    );
    @FormUrlEncoded
    @POST("comment/InputComment.php")
    Call<Comment> InputCommentRequest(
            @Field("idUser") String id_user,
            @Field("idMovie") String id_movie,
            @Field("comment") String comment
    );
    @FormUrlEncoded
    @POST("comment/MovieComment.php")
    Call<Comment> ListComment(
            @Field("idMovie") String id_movie
    );
    @FormUrlEncoded
    @POST("comment/jmlComment.php")
    Call<Comment> jumlahCommentRequest(
            @Field("idMovie") String id_movie
    );
}
