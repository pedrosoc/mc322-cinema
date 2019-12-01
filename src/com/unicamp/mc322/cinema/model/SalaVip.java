package com.unicamp.mc322.cinema.model;

public class SalaVip extends Sala {

	private String cardapio;
	
	public SalaVip(int numeroSala, int capacidade) {
		super(numeroSala, capacidade);
	}

	public String getCardapio() {
		return cardapio;
	}

	public void setCardapio(String cardapio) {
		this.cardapio = cardapio;
	}
	
	

}
