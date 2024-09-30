package org.merdverse.helper;

import java.util.Scanner;

public class Helper {
	
	Scanner scanner = new Scanner(System.in);

	public void cleanConsole() { 
	    System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
	}
	
	public void loading() {
		for (int i = 0; i < 5; i++) { // Loop para simular a barra de carregamento
            System.out.print(".");
            try {
                Thread.sleep(100); // Espera 500 milissegundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restores the interrupted status
            }
		}
	}
	
	 public boolean opcao(String mensagem) {
	        String resposta;
	        String result;
	        while (true) {
	            System.out.print(mensagem);
	            resposta = scanner.nextLine().trim().toUpperCase(); // Lê e normaliza a entrada
	            if (resposta.equals("S")) {
	                return true; // Retorna true se a resposta for "S"
	            } else if (resposta.equals("N")) {

	                return false; // Retorna false se a resposta for "N"
	            } else {
	                System.out.println("Entrada inválida. Tente novamente.");
	            }
	        }

	    }
}
