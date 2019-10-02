package com.unicamp.mc322.cinema.model;

public abstract class Ingresso {

	private float preco;
	private Pessoa comprador;
	private Sessao sessao;
	
	public Ingresso(float preco, Pessoa comprador, Sessao sessao) {
		super();
		this.preco = preco;
		this.comprador = comprador;
		this.setSessao(sessao);
	}

	public abstract String getTipo();
	
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

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	
}
