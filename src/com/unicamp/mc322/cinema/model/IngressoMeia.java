package com.unicamp.mc322.cinema.model;

public class IngressoMeia extends Ingresso {

	public IngressoMeia(float preco, Pessoa comprador, Sessao sessao) {
		super(preco, comprador, sessao);
	}

	@Override
	public String getTipo() {
		return "Meia";
	}

}
