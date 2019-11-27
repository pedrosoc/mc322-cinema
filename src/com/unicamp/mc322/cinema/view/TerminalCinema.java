package com.unicamp.mc322.cinema.view;

import com.sun.xml.internal.ws.handler.HandlerException;
import com.unicamp.mc322.cinema.controller.CarrinhoController;
import com.unicamp.mc322.cinema.controller.CinemaController;
import com.unicamp.mc322.cinema.controller.LoginController;
import com.unicamp.mc322.cinema.model.Cinema;
import com.unicamp.mc322.cinema.model.Ingresso;

import java.util.List;

import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleInt;

public class TerminalCinema {

    private CinemaController cinemaController;

    private CarrinhoController carrinhoController;

    private LoginController loginController;

	public TerminalCinema(Cinema cinema) {
        System.out.println(String.format("Seja bem vindo ao %s", cinema.getNome()));

        this.cinemaController = new CinemaController(cinema);
	    this.carrinhoController = new CarrinhoController();
	    this.loginController = new LoginController();
	}

	public void iniciar() {
        int operacao = this.getAcaoDeslogado();

        while (operacao != 3) {
            try {
                switch (operacao) {
                    case 1:
                        loginController.cadastrarUsuario();
                        break;
                    case 2:
                        loginController.logarUsuario();
                        this.escolherAcoes();
                        loginController.deslogarUsuario();
                        break;
                }
            } catch (HandlerException e) {
                System.out.println();
                System.out.println(e.getCause().getMessage());
            }

            operacao = this.getAcaoDeslogado();
        }
    }

    private void escolherAcoes() {
        int operacao = this.getAcaoLogado();

		while (operacao != 5) {
		    switch (operacao) {
                case 1:

                    // - Aqui deverá fazer um get do terminalde quantos ingressos o usuario deseja comprar

                    // - Talvez seja necessario criar um tipo de ingresso pro carrinho, pois ele precisará
                    //   saber para qual filme/sessao o usuario deseja comprar o ingresso

                    List<Ingresso> ingressosJaReservados = carrinhoController.getIngressos();
                    List<Ingresso> ingressos = cinemaController.reservarIngresso(ingressosJaReservados);
                    carrinhoController.adicionarIngresso(ingressos);
                    break;

                case 2:
                    carrinhoController.exibir();
                    break;

                case 3:

                    // - Nessa momento devera escolher a forma de pagamento e pagar

                    List<Ingresso> ingressosCarrinho = carrinhoController.getIngressos();
                    if (cinemaController.finalizarCompra(ingressosCarrinho))
                        if (carrinhoController.realizarPagamento()) {
                            loginController.registrarCompra(ingressosCarrinho);
                            carrinhoController.limparCarrinho();
                        }

                    break;

                case 4:
                    carrinhoController.limparCarrinho();
                    break;
            }

            operacao = this.getAcaoLogado();
        }
	}

    private int getAcaoLogado() {
        String nome = this.loginController.getUsuarioLogado().getNome();

        while (true) {
            System.out.println();
            System.out.println(String.format("Bem vindo %s", nome));
            System.out.println();
            System.out.println("Digite:");
            System.out.println("1 - Comprar ingresso");
            System.out.println("2 - Visualizar carrinho");
            System.out.println("3 - Pagar ingressos do carrinho");
            System.out.println("4 - Limpar carrinho");
            System.out.println("5 - Sair");
            System.out.println();

            int op = getSimpleInt("a opção desejada");

            if (op != 1 && op != 2 && op != 3 && op != 4 && op != 5)
                continue;

            return op;
        }
    }

    private int getAcaoDeslogado() {
        while (true) {
            System.out.println();
            System.out.println("Menu");
            System.out.println();
            System.out.println("Digite:");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Logar");
            System.out.println("3 - Desligar máquina");
            System.out.println();

            int op = getSimpleInt("a opção desejada");

            if (op != 1 && op != 2 && op != 3)
                continue;

            return op;
        }
    }

}
