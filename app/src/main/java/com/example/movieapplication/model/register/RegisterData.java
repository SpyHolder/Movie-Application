package com.example.movieapplication.model.register;

import com.google.gson.annotations.SerializedName;

public class RegisterData {

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