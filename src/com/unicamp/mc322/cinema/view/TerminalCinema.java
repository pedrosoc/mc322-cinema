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

	public TerminalCinema() {
        this.cinemaController = new CinemaController();
	    this.carrinhoController = new CarrinhoController();
	    this.loginController = new LoginController();
	}

	public void iniciar() {
        int operacao = 0;

        while (operacao != 3) {
            try {
                operacao = this.getAcaoDeslogado();

                switch (operacao) {
                    case 1:
                        loginController.cadastrarUsuario();
                        break;
                    case 2:
                        loginController.logarUsuario();
                        this.escolherAcoes();
                        loginController.deslogarUsuario();
                        break;
                    default:
                        break;
                }
            } catch (HandlerException e) {
                System.out.println();
                System.out.println(e.getCause().getMessage());
            }
        }
    }

    private void escolherAcoes() {
        int operacao = 0;

        while (operacao != 5) {
		    try {
                operacao = this.getAcaoLogado();

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
                                cinemaController.atualizarCinema();
                                carrinhoController.limparCarrinho();
                            }

                        break;

                    case 4:
                        carrinhoController.limparCarrinho();
                        break;

                    default:
                        break;
                }
            } catch (HandlerException e) {
                System.out.println();
                System.out.println(e.getCause().getMessage());
            }
        }
	}

    private int getAcaoLogado() throws HandlerException {
        String nome = this.loginController.getUsuarioLogado().getNome();

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
            throw new HandlerException(new Throwable("Opção inválida!"));

        return op;
    }

    private int getAcaoDeslogado() throws HandlerException {
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
            throw new HandlerException(new Throwable("Opção inválida!"));

        return op;
    }

}
