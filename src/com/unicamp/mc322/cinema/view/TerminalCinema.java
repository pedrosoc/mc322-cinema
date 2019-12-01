package com.unicamp.mc322.cinema.view;

import com.sun.xml.internal.ws.handler.HandlerException;
import com.unicamp.mc322.cinema.facade.CinemaFacade;
import com.unicamp.mc322.cinema.facade.LoginFacade;
import com.unicamp.mc322.cinema.model.Ingresso;

import java.util.List;

import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleInt;

public class TerminalCinema {

   private CinemaFacade cinemaFacade;
   private LoginFacade loginFacade;

	public TerminalCinema() {
	    this.cinemaFacade = new CinemaFacade();
        this.loginFacade = new LoginFacade();
	}

	public void iniciar() {
        int operacao = 0;

        while (operacao != 3) {
            try {
                operacao = this.getAcaoDeslogado();

                switch (operacao) {
                    case 1:
                    	loginFacade.cadastrarUsuario();
                        break;
                    case 2:
                    	loginFacade.logarUsuario();
                        this.escolherAcoes();
                        loginFacade.deslogarUsuario();
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

                        cinemaFacade.reservarIngresso();
                        break;

                    case 2:
                    	cinemaFacade.exibirItensCarrinho();
                        break;

                    case 3:

                        // - Nessa momento devera escolher a forma de pagamento e pagar
                        List<Ingresso> ingressosCarrinho = cinemaFacade.getIngressosCarrinho();
                        if (cinemaFacade.finalizarCompra(ingressosCarrinho))
                            	loginFacade.registrarCompra(ingressosCarrinho);

                        break;

                    case 4:
                    	cinemaFacade.limparCarrinho();
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
        String nome = this.loginFacade.getUsuarioLogado().getNome();

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
