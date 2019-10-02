package com.unicamp.mc322.cinema.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;

public class Pagador {
	
	public static boolean pagarViaCartao(Carrinho car) {
		System.out.println("Digite o numero do seu cartao");
		System.out.println("Digite o titular");
		System.out.println("Digite a data de vencimento");
		System.out.println("Digite o codigo de validacao");
		System.out.println("Deseja realizar a compra dos seguintes itens ?");
		car.listarItens();
		System.out.println("Forma de Pagamento: Cartao");
		int sucesso = Pagador.confirmar();
		if(sucesso == 1) {
			System.out.println("Compra realizada com sucesso");			
			return true;
		}else {
			System.out.println("Compra cancelada");
			return false;
		}
	}
	
	public static boolean pagarViaBoleto(Carrinho car) {
		System.out.println("Deseja realizar a compra dos seguintes itens ?");
		car.listarItens();		
		System.out.println("Forma de Pagamento: Boleto");
		int sucesso = Pagador.confirmar();
		if(sucesso == 1) {
			System.out.println("Compra realizada com sucesso");
			System.out.println("Codigo: 123456789");
			return true;
		}else {
			System.out.println("Compra cancelada");
			return false;
		}
	}
	
	private static int confirmar() {
        try {
            Reader r = new BufferedReader(new InputStreamReader(System.in));
            StreamTokenizer st = new StreamTokenizer(r);

            System.out.println(String.format("0-Nao 1-Sim"));

            st.nextToken();

            return ((int) st.nval);
        } catch (IOException e) {
            System.out.println("Erro na leitura do teclado");
            return (0);
        }
    }


}
