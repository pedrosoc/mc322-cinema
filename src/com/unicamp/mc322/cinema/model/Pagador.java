package com.unicamp.mc322.cinema.model;

import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleInt;
import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleString;

public class Pagador {
	
	public static boolean pagarViaCartao(Carrinho car) {
		System.out.println();
		getSimpleString("o numero do seu cartao");
		getSimpleString("o titular");
		getSimpleString("a data de vencimento");
		getSimpleString("o codigo de validacao");
		System.out.println();
		System.out.println("Você confirma a compra dos seguintes itens?");
		car.listarItens();
		System.out.println();
		System.out.println("Forma de Pagamento: Cartao");
		System.out.println();
		int sucesso = Pagador.confirmar();
		if(sucesso == 1) {
			System.out.println();
			System.out.println("Compra realizada com sucesso!");
			return true;
		}else {
			System.out.println();
			System.out.println("Compra cancelada!");
			return false;
		}
	}
	
	public static boolean pagarViaBoleto(Carrinho car) {
		System.out.println();
		System.out.println("Você confirma a compra dos seguintes itens?");
		car.listarItens();
		System.out.println();
		System.out.println("Forma de Pagamento: Boleto");
		System.out.println();
		int sucesso = Pagador.confirmar();
		if(sucesso == 1) {
			System.out.println();
			System.out.println("Compra realizada com sucesso!");
			System.out.println("Codigo: 123456789");
			return true;
		}else {
			System.out.println();
			System.out.println("Compra cancelada!");
			return false;
		}
	}
	
	private static int confirmar() {
		while (true) {
			System.out.println();
			System.out.println("Digite:");
			System.out.println("0 - Não");
			System.out.println("1 - Sim");
			System.out.println();

			int op = getSimpleInt("a opção desejada");

			if (op != 0 && op != 1)
				continue;

			return op;
		}
    }


}
