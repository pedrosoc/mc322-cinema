package com.unicamp.mc322.cinema.factory;

import com.unicamp.mc322.cinema.model.SalaComum;
import com.unicamp.mc322.cinema.model.SalaVip;

public final class SalaFactory {

	private SalaFactory() {
		super();
	}
	
	public static SalaComum criarSalaComum(int numeroSala, int capacidade) {
		return new SalaComum(numeroSala, capacidade);
	}
	
	public static SalaVip criarSalaVip(int numeroSala, int capacidade) {
		return new SalaVip(numeroSala, capacidade);
	}
	
}
