package com.unicamp.mc322.cinema.controller;

import com.unicamp.mc322.cinema.model.Carrinho;
import com.unicamp.mc322.cinema.model.Ingresso;
import com.unicamp.mc322.cinema.model.Pagador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.List;

public class CarrinhoController {
	
	private Carrinho carrinho;
	
    public CarrinhoController() {
        this.carrinho = new Carrinho();
    }

    public void exibir() {
    	System.out.println("Seu carrinho contem: ");
    	this.carrinho.listarItens();
    }

    public List<Ingresso> getIngressos() {
    	return carrinho.getIngressos();
    }
    
    public void adicionarIngresso(Ingresso ingresso) {
		this.carrinho.addIngresso(ingresso);
		
	}

    public void limparCarrinho() {
    	carrinho.limparCarrinho();
    }
    
    public void realizarPagamento() {
    	int forma = formaPagamento();
    	boolean sucesso= false;
    	if(forma == 0) {
    		sucesso = Pagador.pagarViaCartao(this.carrinho);
    	}else if(forma == 1){
    		sucesso = Pagador.pagarViaBoleto(this.carrinho);
    	}
    	
    	if(sucesso) {
    		limparCarrinho();
    	}
    }
    
    private static int formaPagamento() {
        try {
            Reader r = new BufferedReader(new InputStreamReader(System.in));
            StreamTokenizer st = new StreamTokenizer(r);
            System.out.println("Qual metodo de pagamento deseja realizar? ");
            System.out.println(String.format("0-Cartao 1-Boleto"));

            st.nextToken();

            return ((int) st.nval);
        } catch (IOException e) {
            System.out.println("Erro na leitura do teclado");
            return (0);
        }
    }

}
