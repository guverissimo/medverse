package org.merdverse;

import java.time.LocalDate;
import java.util.Scanner;

import org.merdverse.dao.AlunoDAO;
import org.merdverse.dao.Entrar;
import org.merdverse.dao.ProfessorDAO;
import org.merdverse.models.Professor;
import org.merdverse.models.Usuario;

public class Main {
	public static final String RESET = "\033[0m";
    public static final String VERDE = "\033[0;32m"; // Verde
    public static final String AZUL = "\033[0;34m";  // Azul
    public static final String AMARELO = "\033[0;33m"; // Amarelo
	
    public static void main(String[] args) {
    	// Logo do Projeto
        System.out.println(AZUL + "**********************************************");
        System.out.println(AZUL + "  __  __          ___      __                \n"
        		+ " |  \\/  |        | \\ \\    / /                \n"
        		+ " | \\  / | ___  __| |\\ \\  / /__ _ __ ___  ___ \n"
        		+ " | |\\/| |/ _ \\/ _` | \\ \\/ / _ | '__/ __|/ _ \\\n"
        		+ " | |  | |  __| (_| |  \\  |  __| |  \\__ |  __/\n"
        		+ " |_|  |_|\\___|\\__,_|   \\/ \\___|_|  |___/\\___|\n"
        		+ "                                             \n"
        		+ "                                             ");
        System.out.println(AZUL + "**********************************************" + RESET);
        
        Entrar entrar = new Entrar();
        Scanner scanner = new Scanner(System.in);
        
        // DAOs
        AlunoDAO alunoDAO = new AlunoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        
        // Iniciando um professor no sistema
        LocalDate p1DataNasc = LocalDate.of(1990, 5, 20);
        Professor p1 = new Professor("Felipe Souto", "felipe.souto@fiap.com.br", p1DataNasc);
 
        professorDAO.create(p1);
    	
    	boolean exec = true;
    	
    	while(exec) {
    		boolean logado = false;
    		System.out.println(AMARELO + "Login." + RESET);
    		System.out.println("Email: ");
    		String email = scanner.next();
    		scanner.nextLine(); // Limpar o buffer
    		System.out.println("Senha: ");
    		String senha = scanner.next();
    		scanner.nextLine(); // Limpar o buffer
    		
            int usr = entrar.entrar(email, senha);

            switch (usr) {
                case 0:
                    System.out.println("Você está logado como Aluno.");
                    logado = true; // Define que o usuário está logado
                    break;
                case 1:
                    System.out.println("Você está logado como Professor.");
                    logado = true; // Define que o usuário está logado
                    break;
                case 2:
                    System.out.println("E-mail ou senha inválidos.");
                    break;
                case -1:
                    System.out.println("Erro ao realizar o login. Tente novamente.");
                    break;
                default:
                    System.out.println("Opção desconhecida.");
            }

            // Se o usuário estiver logado, pode sair do loop ou realizar outras ações
            if (logado) {
                exec = false; // Sai do loop
            }
        }
    		
    		
    	}
    	
    }