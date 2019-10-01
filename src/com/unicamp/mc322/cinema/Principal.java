package com.unicamp.mc322.cinema;

import com.unicamp.mc322.cinema.model.Cinema;
import com.unicamp.mc322.cinema.view.TerminalCinema;

public class Principal {

    public static void main(String args[]) {

        Cinema cinema = new Cinema("Cinemark", "Campinas", "123");
        TerminalCinema terminal = new TerminalCinema(cinema);
        terminal.iniciar();

    }

}
