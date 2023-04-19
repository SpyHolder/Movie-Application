package com.example.movieapplication.model.movie;

import com.google.gson.annotations.SerializedName;

public class MovieData {

	@SerializedName("poster_film")
	private String posterFilm;

	@SerializedName("rating_usia")
	private String ratingUsia;

	@SerializedName("sutradara")
	private String sutradara;

	@SerializedName("tanggal_liris")
	private String tanggalLiris;

	@SerializedName("rating")
	private String rating;

	@SerializedName("genre")
	private String genre;

	@SerializedName("id")
	private String id;

	@SerializedName("durasi")
	private String durasi;

	@SerializedName("nama_film")
	private String namaFilm;

	@SerializedName("pemain")
	private String pemain;

	@SerializedName("sinopsis")
	private String sinopsis;

	public void setPosterFilm(String posterFilm){
		this.posterFilm = posterFilm;
	}

	public String getPosterFilm(){
		return posterFilm;
	}

	public void setRatingUsia(String ratingUsia){
		this.ratingUsia = ratingUsia;
	}

	public String getRatingUsia(){
		return ratingUsia;
	}

	public void setSutradara(String sutradara){
		this.sutradara = sutradara;
	}

	public String getSutradara(){
		return sutradara;
	}

	public void setTanggalLiris(String tanggalLiris){
		this.tanggalLiris = tanggalLiris;
	}

	public String getTanggalLiris(){
		return tanggalLiris;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setGenre(String genre){
		this.genre = genre;
	}

	public String getGenre(){
		return genre;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDurasi(String durasi){
		this.durasi = durasi;
	}

	public String getDurasi(){
		return durasi;
	}

	public void setNamaFilm(String namaFilm){
		this.namaFilm = namaFilm;
	}

	public String getNamaFilm(){
		return namaFilm;
	}

	public void setPemain(String pemain){
		this.pemain = pemain;
	}

	public String getPemain(){
		return pemain;
	}

	public void setSinopsis(String sinopsis){
		this.sinopsis = sinopsis;
	}

	public String getSinopsis(){
		return sinopsis;
	}
}