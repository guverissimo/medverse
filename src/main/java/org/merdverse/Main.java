package org.merdverse;

import java.time.LocalDate;
import java.util.Scanner;

import org.merdverse.dao.AlunoDAO;
import org.merdverse.dao.Entrar;
import org.merdverse.dao.ProfessorDAO;
import org.merdverse.helper.Helper;
import org.merdverse.models.Aluno;
import org.merdverse.models.LoginResult;
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
        Helper helper = new Helper();
        
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
    		System.out.print("Email: ");
    		String email = scanner.next();
    		scanner.nextLine(); // Limpar o buffer
    		System.out.print("Senha: ");
    		String senha = scanner.next();
    		scanner.nextLine(); // Limpar o buffer
    		
    		LoginResult usr = entrar.entrar(email, senha);
    		System.out.println(usr);
    		if (usr != null) {
    			logado = true;
    			
    			while (logado) {
    				Usuario usuarioLogado = usr.getUsuario();
    				helper.loading();
    				helper.cleanConsole();
    				System.out.println("Olá, " + usuarioLogado.getNome() + "!");
    				System.out.println(AMARELO + "Menu: " + RESET);
    				if (usr.getCodigo() == 0) {
    					// Aluno
    					System.out.println("1 - Entrar no treinamento");
    					
    				} else if (usr.getCodigo() == 1) {
    					// Professor
    					System.out.println("8 - Cadastrar aluno");
    					System.out.println("9 - Alterar sua senha");
    					System.out.println("0 - Sair");
    					System.out.print("Opção: ");
    					int opcao = scanner.nextInt();
    					scanner.nextLine();
    					
    					switch (opcao) {
    					case 8:
    						System.out.println("Cadastrar aluno");
    						
    						System.out.print("Nome: ");
					        String nomeAluno = scanner.nextLine();
					        
					        System.out.print("Email: ");
					        String emailAluno = scanner.nextLine();
					        
					        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
					        String dataNascStr = scanner.nextLine();
					        LocalDate dataNascAluno = LocalDate.parse(dataNascStr);
					        
					        Aluno aln = new Aluno(nomeAluno, emailAluno, dataNascAluno);
					        alunoDAO.create(aln);
					        helper.loading();
					        break;
    					
    					case 9:
    						System.out.println("Alterar sua senha");
    						System.out.print("Digite sua senha atual: ");
    						String senhaAntiga = scanner.next();
    						
    						while (!senhaAntiga.equals(usr.getUsuario().getSenha())) {
    							System.out.print("Senha incorreta, digite novamente: ");
    							senhaAntiga = scanner.next();
    						}
    						
    						System.out.print("Digite sua nova senha: ");
    						String novaSenha = scanner.next();
    						professorDAO.updatePassword(usr.getUsuario().getEmail(), novaSenha);
    						usr.getUsuario().setSenha(novaSenha);
    						helper.loading();
    						break;
    					case 0:
    						System.out.println("Saindo...");
    						helper.loading();
    						logado = false;
    					}
    					
    				}
    			}
            } else {
                System.out.println("E-mail ou senha inválidos.");
            }

            if (logado) {
                exec = false; // Sai do loop
            }
        }
    		
    		
    	}
    	
    }