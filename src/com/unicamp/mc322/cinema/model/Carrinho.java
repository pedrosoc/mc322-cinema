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
		for (Ingresso ig : this.ingressos) {
			System.out
					.println("Comprador: " + ig.getComprador().getNome() + " Tipo: " + ig.getTipo() + " Preco: " + ig.getPreco() + " reais");
		}
		System.out.println("Preco Total: "+this.precoTotal);
	}
	
	public List<Ingresso> getIngressos(){
		return this.ingressos;
	}
	
	public void limparCarrinho() {
		this.ingressos.clear();
		
	}
}
