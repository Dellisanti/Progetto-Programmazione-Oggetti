package com.example.ProgettoProgrammazioneOggetti.model;

public class Prodotto {
	private int id;
	private String nome;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}
	
}
