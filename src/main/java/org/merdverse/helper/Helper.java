package org.merdverse.helper;

public class Helper {

	public void cleanConsole() { 
	    System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
	}
	
	public void loading() {
		for (int i = 0; i < 5; i++) { // Loop para simular a barra de carregamento
            System.out.print(".");
            try {
                Thread.sleep(500); // Espera 500 milissegundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restores the interrupted status
            }
		}
	}
}
