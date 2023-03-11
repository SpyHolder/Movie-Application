package com.example.movieapplication.model.list_movie;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class List_Movie {

	@SerializedName("data")
	private List<List_MovieData> data;

	@SerializedName("status")
	private boolean status;

	public List<List_MovieData> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}