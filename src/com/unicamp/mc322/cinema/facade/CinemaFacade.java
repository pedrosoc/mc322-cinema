package com.unicamp.mc322.cinema.facade;

import com.sun.xml.internal.ws.handler.HandlerException;
import com.unicamp.mc322.cinema.controller.CarrinhoController;
import com.unicamp.mc322.cinema.controller.CinemaController;
import com.unicamp.mc322.cinema.model.Ingresso;

import java.util.List;

public class CinemaFacade {

	private CinemaController cinemaController;
	private CarrinhoController carrinhoController;

	public CinemaFacade() {
		this.cinemaController = new CinemaController();
		this.carrinhoController = new CarrinhoController();
	}

	public void exibirItensCarrinho() {
		carrinhoController.exibir();
	}

	public List<Ingresso> getIngressosCarrinho() {
		return carrinhoController.getIngressos();
	}

	public void adicionarIngressoAoCarrinho(List<Ingresso> ingressos) {
		carrinhoController.adicionarIngresso(ingressos);

	}

	public void limparCarrinho() {
		carrinhoController.limparCarrinho();
	}

	public boolean realizarPagamento() throws HandlerException {
		return carrinhoController.realizarPagamento();
	}
	
	//----------------------------------------------------------------------------------#------------------------------------------------------------------------------
	
	public void reservarIngresso() throws HandlerException {
		List<Ingresso> ingressos = carrinhoController.getIngressos();
		List<Ingresso> reservados = cinemaController.reservarIngresso(ingressos);
		carrinhoController.adicionarIngresso(reservados);
	}
	
	public boolean finalizarCompra(List<Ingresso> ingressos) {
		if (cinemaController.finalizarCompra(ingressos))
			if (carrinhoController.realizarPagamento()) {
				cinemaController.atualizarCinema();
				carrinhoController.limparCarrinho();
				return true;
			}

        return false;
    }

}
