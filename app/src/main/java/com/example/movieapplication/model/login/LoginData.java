package com.example.movieapplication.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}
}