package com.example.movieapplication.model.register;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Register{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<RegisterData> data;

	@SerializedName("status")
	private boolean status;

	public String getPesan(){
		return pesan;
	}

	public List<RegisterData> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}