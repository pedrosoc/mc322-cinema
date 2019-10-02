package com.unicamp.mc322.cinema.model;

public class IngressoInteira extends Ingresso {

	public IngressoInteira(float preco, Pessoa comprador, Sessao sessao) {
		super(preco, comprador, sessao);
	}

	@Override
	public String getTipo() {
		return "Inteira";
	}

}
