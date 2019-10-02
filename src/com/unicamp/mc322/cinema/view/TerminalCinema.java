package com.unicamp.mc322.cinema.view;

import com.unicamp.mc322.cinema.controller.CarrinhoController;
import com.unicamp.mc322.cinema.controller.CinemaController;
import com.unicamp.mc322.cinema.model.Cinema;
import com.unicamp.mc322.cinema.model.Ingresso;

import java.util.List;

import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleInt;

public class TerminalCinema {

    private CinemaController cinemaController;

    private CarrinhoController carrinhoController;

	public TerminalCinema(Cinema cinema) {
        System.out.println(String.format("Seja bem vindo ao %s", cinema.getNome()));

        this.cinemaController = new CinemaController(cinema);
	    this.carrinhoController = new CarrinhoController();
	}

	public void iniciar() {
		int operacao = this.getOperacao();

		while (operacao != 5) {
		    switch (operacao) {
                case 1:

                    // - Aqui deverá fazer um get do terminalde quantos ingressos o usuario deseja comprar

                    // - Talvez seja necessario criar um tipo de ingresso pro carrinho, pois ele precisará
                    //   saber para qual filme/sessao o usuario deseja comprar o ingresso

                    List<Ingresso> ingressos = cinemaController.reservarIngresso();
                    carrinhoController.adicionarIngresso(ingressos);
                    break;

                case 2:
                    carrinhoController.exibir();
                    break;

                case 3:

                    // - Nessa momento devera escolher a forma de pagamento e pagar

                    List<Ingresso> ingressosCarrinho = carrinhoController.getIngressos();
                    cinemaController.finalizarCompra(ingressosCarrinho);
                    break;

                case 4:
                    List<Ingresso> ingressosReservados = carrinhoController.getIngressos();
                    cinemaController.cancelarReservas(ingressosReservados);
                    carrinhoController.limparCarrinho();
                    break;
            }

            operacao = this.getOperacao();
        }
	}

    private int getOperacao() {
        while (true) {
            System.out.println();
            System.out.println("Menu");
            System.out.println();
            System.out.println("Digite:");
            System.out.println("1 - Comprar ingresso");
            System.out.println("2 - Visualizar carrinho");
            System.out.println("3 - Pagar ingressos do carrinho");
            System.out.println("4 - Limpar carrinho");
            System.out.println("5 - Sair");
            System.out.println();

            int op = getSimpleInt("a opção desejada");

            if (op != 1 && op != 2 && op != 3 && op != 4)
                continue;

            return op;
        }
    }

}
