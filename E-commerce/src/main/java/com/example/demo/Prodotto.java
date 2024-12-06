package com.example.demo;

public class Prodotto {
	private String nome;
	private String marca;
	private double prezzo;
	private String luogo;
	private String url;

	public Prodotto(String nome, String marca, double prezzo, String luogo, String url) {
		this.nome = nome;
		this.marca = marca;
		this.prezzo = prezzo;
		this.luogo = luogo;
		this.url = url;
	}

	// Getters and Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static class ProdottoA {
		private Prodotto prodotto;
		private int quantita;

		public ProdottoA(Prodotto prodotto, int quantita) {
			this.prodotto = prodotto;
			this.quantita = quantita;
		}

		public Prodotto getProdotto() {
			return prodotto;
		}

		public int getQuantita() {
			return quantita;
		}

		public double getSubtotale() {
			return prodotto.getPrezzo() * quantita;
		}
	}
}
