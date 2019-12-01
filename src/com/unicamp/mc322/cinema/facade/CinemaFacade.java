package com.unicamp.mc322.cinema.facade;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sun.xml.internal.ws.handler.HandlerException;
import com.unicamp.mc322.cinema.controller.CarrinhoController;
import com.unicamp.mc322.cinema.controller.CinemaController;
import com.unicamp.mc322.cinema.model.Cinema;
import com.unicamp.mc322.cinema.model.Filme;
import com.unicamp.mc322.cinema.model.Ingresso;
import com.unicamp.mc322.cinema.model.Pagador;
import com.unicamp.mc322.cinema.model.Sala;
import com.unicamp.mc322.cinema.model.Sessao;

public class CinemaFacade {

	private CinemaController cinemaController;
	private CarrinhoController carrinhoController;

	public CinemaFacade(Cinema cinema) {
		this.cinemaController = new CinemaController(cinema);
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
	
	public List<Ingresso> reservarIngresso(List<Ingresso> ingressosCarrinho) throws HandlerException {
		return cinemaController.reservarIngresso(ingressosCarrinho);
    }
	
	public boolean finalizarCompra(List<Ingresso> ingressos) {
        return cinemaController.finalizarCompra(ingressos);
    }

}
