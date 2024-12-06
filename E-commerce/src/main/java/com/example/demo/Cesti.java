package com.example.demo;

public class Cesti {
	private String nome;
	private double prezzo;

	public Cesti(String nome, double prezzo) {
		this.nome = nome;
		this.prezzo = prezzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public static class CestiA {
		private String nome;
		private double prezzo;
		private int quantita;

		public CestiA(Cesti cesto, int quantita) {
			this.nome = cesto.getNome();
			this.prezzo = cesto.getPrezzo();
			this.quantita = quantita;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public double getPrezzo() {
			return prezzo;
		}

		public void setPrezzo(double prezzo) {
			this.prezzo = prezzo;
		}

		public int getQuantita() {
			return quantita;
		}

		public void setQuantita(int quantita) {
			this.quantita = quantita;
		}
	}
}
