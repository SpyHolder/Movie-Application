package com.example.movieapplication.model.login;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Login {

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private ArrayList<LoginData> data;

	@SerializedName("status")
	private boolean status;

	public String getPesan(){
		return pesan;
	}

	public ArrayList<LoginData> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}