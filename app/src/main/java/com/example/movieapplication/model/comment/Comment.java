package com.example.movieapplication.model.comment;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Comment{

	@SerializedName("data")
	private ArrayList<CommentData> data;

	@SerializedName("status")
	private boolean status;

	public ArrayList<CommentData> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}