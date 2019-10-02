package com.unicamp.mc322.cinema.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;

public class TerminalUtil {

    public static int getSimpleInt(String str) {
        try {
            Reader r = new BufferedReader(new InputStreamReader(System.in));
            StreamTokenizer st = new StreamTokenizer(r);

            System.out.print(String.format("Digite %s: ", str));

            st.nextToken();

            return ((int) st.nval);
        } catch (IOException e) {
            System.out.println("Erro na leitura do teclado");
            return (0);
        }
    }

}
