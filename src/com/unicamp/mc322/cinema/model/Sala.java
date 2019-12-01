package com.unicamp.mc322.cinema.model;

import java.util.ArrayList;
import java.util.List;

public abstract class  Sala {
	private int capacicade;
	private int numeroSala;
	private List<Sessao> sessoes;
	
	public Sala(int numeroSala, int capacidade) {
		this.setCapacidade(capacidade);
		this.sessoes = new ArrayList<>();
		this.numeroSala = numeroSala;
	}
	
	public void setCapacidade(int capacidade) {
		if(capacidade > -1)
			this.capacicade = capacidade;
	}
	public int getCapacicade() {
		return capacicade;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void addSessao(Sessao sessao) {
		this.sessoes.add(sessao);
	}
	
	public void removeSessao(Sessao sessao) {
		this.sessoes.remove(sessao);
	}
}
