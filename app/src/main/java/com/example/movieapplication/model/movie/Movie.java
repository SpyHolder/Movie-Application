package com.example.movieapplication.model.movie;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Movie{

	@SerializedName("data")
	private ArrayList<MovieData> data;

	@SerializedName("status")
	private boolean status;

	public void setData(ArrayList<MovieData> data){
		this.data = data;
	}

	public ArrayList<MovieData> getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}