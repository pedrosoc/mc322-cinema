package com.unicamp.mc322.cinema.model;

public abstract class Ingresso {

	private float preco;
	private Pessoa comprador;
	
	public Ingresso(float preco, Pessoa comprador) {
		super();
		this.preco = preco;
		this.comprador = comprador;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public Pessoa getComprador() {
		return comprador;
	}
	
	public void setComprador(Pessoa comprador) {
		this.comprador = comprador;
	}
	
}
