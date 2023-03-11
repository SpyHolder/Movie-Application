package com.example.movieapplication.model.list_movie;

import com.google.gson.annotations.SerializedName;

public class List_MovieData {

	@SerializedName("awal_penayangan")
	private String awalPenayangan;

	@SerializedName("akhir_penayangan")
	private String akhirPenayangan;

	@SerializedName("harga_tiket")
	private int hargaTiket;

	@SerializedName("tanggal_liris")
	private String tanggalLiris;

	@SerializedName("genre")
	private String genre;

	@SerializedName("poster")
	private String poster;

	@SerializedName("nama_film")
	private String namaFilm;

	@SerializedName("sinopsis")
	private String sinopsis;

	public String getAwalPenayangan(){
		return awalPenayangan;
	}

	public String getAkhirPenayangan(){
		return akhirPenayangan;
	}

	public int getHargaTiket(){
		return hargaTiket;
	}

	public String getTanggalLiris(){
		return tanggalLiris;
	}

	public String getGenre(){
		return genre;
	}

	public String getPoster(){
		return poster;
	}

	public String getNamaFilm(){
		return namaFilm;
	}

	public String getSinopsis(){
		return sinopsis;
	}
}