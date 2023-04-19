package com.example.movieapplication.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("img_user")
	private String imgUser;

	@SerializedName("name")
	private String name;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("email")
	private String email;

	public String getImgUser(){
		return imgUser;
	}

	public String getName(){
		return name;
	}

	public String getIdUser(){
		return idUser;
	}

	public String getEmail(){
		return email;
	}
}