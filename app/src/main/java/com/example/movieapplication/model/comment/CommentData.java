package com.example.movieapplication.model.comment;

import com.google.gson.annotations.SerializedName;

public class CommentData {

	@SerializedName("img_profile")
	private String imgProfile;

	@SerializedName("like")
	private String like;

	@SerializedName("dislike")
	private String dislike;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("id_movie")
	private String idMovie;

	@SerializedName("comment")
	private String comment;

	@SerializedName("id_comment")
	private String idComment;

	@SerializedName("jumlah_comment")
	private String jumlahComment;

	public String getImgProfile(){
		return imgProfile;
	}

	public String getLike(){
		return like;
	}

	public String getDislike(){
		return dislike;
	}

	public String getUserName(){
		return userName;
	}

	public String getIdMovie(){
		return idMovie;
	}

	public String getComment(){
		return comment;
	}

	public String getIdComment(){
		return idComment;
	}
	public String getJumlahComment() {
		return jumlahComment;
	}
}