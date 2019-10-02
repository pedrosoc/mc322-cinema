package com.unicamp.mc322.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	private List<Ingresso> ingressos;
	private float precoTotal;

	public Carrinho() {
		ingressos = new ArrayList<Ingresso>();
		precoTotal = 0;

	}

	public void addIngresso(Ingresso ingresso) {
		ingressos.add(ingresso);
		precoTotal += ingresso.getPreco();
	}

	public void listarItens() {
		for (Ingresso ig : this.ingressos) {
			System.out
					.println("Comprador: " + ig.getComprador() + " Tipo: " + ig.getTipo() + " Preco: " + ig.getPreco());
		}
	}
}
