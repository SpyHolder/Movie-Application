package com.example.movieapplication.model.movie;

import com.google.gson.annotations.SerializedName;

public class Movie{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("status")
	private boolean status;

	public String getPesan(){
		return pesan;
	}

	public boolean isStatus(){
		return status;
	}
}