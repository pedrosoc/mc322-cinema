package com.unicamp.mc322.cinema.facade;

import com.sun.xml.internal.ws.handler.HandlerException;
import com.unicamp.mc322.cinema.controller.LoginController;
import com.unicamp.mc322.cinema.model.Ingresso;
import com.unicamp.mc322.cinema.model.Pessoa;

import java.util.List;

public class LoginFacade {
	
	private LoginController loginController;
	
	public LoginFacade() {
		 this.loginController = new LoginController();
	}
	public void cadastrarUsuario() throws HandlerException {
      loginController.cadastrarUsuario();
    }

    public Pessoa getUsuarioLogado() {
        return loginController.getUsuarioLogado();
    }

    public boolean logarUsuario() throws HandlerException {
        return loginController.logarUsuario();
    }

    public boolean deslogarUsuario() throws HandlerException {
    	return loginController.deslogarUsuario();
    }

    public void registrarCompra(List<Ingresso> ingressos) {
       loginController.registrarCompra(ingressos);
    }
}
