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

	public void addIngresso(List<Ingresso> ingressos) {
		this.ingressos.addAll(ingressos);

		for (Ingresso ingresso: ingressos) {
			this.precoTotal += ingresso.getPreco();
		}
	}

	public void listarItens() {
		for (int i = 0; i < this.ingressos.size(); i++) {
			Ingresso ig = this.ingressos.get(i);

			System.out.println();
			System.out.println(String.format("Ingresso nº %d", i + 1));
			System.out.println(String.format("Comprador: %s", ig.getComprador().getNome()));
			System.out.println(String.format("Tipo: %s", ig.getTipo()));
			System.out.println(String.format("Preco: R$ %.2f", ig.getPreco()));
		}

		System.out.println();
		System.out.println("Preco Total: "+this.precoTotal);
	}
	
	public List<Ingresso> getIngressos(){
		return this.ingressos;
	}
	
	public void limparCarrinho() {
		this.ingressos.clear();
		precoTotal = 0;
		
	}
}
