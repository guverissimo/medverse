package org.merdverse;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.merdverse.dao.AlunoDAO;
import org.merdverse.dao.Entrar;
import org.merdverse.dao.ProfessorDAO;
import org.merdverse.dao.TreinamentoDAO;
import org.merdverse.helper.Helper;
import org.merdverse.models.Aluno;
import org.merdverse.models.LoginResult;
import org.merdverse.models.Professor;
import org.merdverse.models.Sessao;
import org.merdverse.models.Treinamento;
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
        TreinamentoDAO treinamentoDAO = new TreinamentoDAO();
        
        // Iniciando um professor no sistema
        LocalDate p1DataNasc = LocalDate.of(1990, 5, 20);
        Professor p1 = new Professor("Felipe Souto", "felipe.souto@fiap.com.br", p1DataNasc);
        professorDAO.create(p1);
        
        // Iniciando um aluno no sistema
        LocalDate a1DataNasc = LocalDate.of(2003, 12, 12);
        Aluno a1 = new Aluno("Gustavo Veríssimo", "gustavo@fiap.com.br", a1DataNasc);
        
        // Iniciando um treinamento no sistema
        Treinamento treinamento1 = new Treinamento("TR01", "Treinamento Inicial", 100, 60, p1.getEmail());
    	
    	boolean exec = true;
    	
    	while(exec) {
    		boolean logado = false;
//    		System.out.println(AMARELO + "Login." + RESET);
//    		System.out.print("Email: ");
//    		String email = scanner.next();
//    		scanner.nextLine(); // Limpar o buffer
//    		System.out.print("Senha: ");
//    		String senha = scanner.next();
//    		scanner.nextLine(); // Limpar o buffer
//    		
//    		LoginResult usr = entrar.entrar(email, senha);
    		LoginResult usr = entrar.entrar("felipe.souto@fiap.com.br", "200590");
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
    					System.out.println("9 - Alterar sua senha");
    					System.out.println("0 - Sair");
    					System.out.print("Opção: ");
    					int opcao = scanner.nextInt();
    					scanner.nextLine();
    					switch (opcao) {
    					case 1:
    						helper.cleanConsole();
    						System.out.println("Iniciando treinamento...");
    						helper.loading();
    						System.out.println(treinamento1.getId() + " " + treinamento1.getNome());
    						
    						Sessao sessao = new Sessao(usr.getUsuario().getEmail(), treinamento1);
    						sessao.iniciarSim(sessao);
    						
    						
    							
    					case 9: // Alterar senha
    						helper.cleanConsole();
    						System.out.println("Alterar sua senha");
    						System.out.print("Digite sua senha atual: ");
    						String senhaAntiga = scanner.next();
    						
    						while (!senhaAntiga.equals(usr.getUsuario().getSenha())) {
    							System.out.print("Senha incorreta, digite novamente: ");
    							senhaAntiga = scanner.next();
    						}
    						
    						System.out.print("Digite sua nova senha: ");
    						String novaSenha = scanner.next();
    						alunoDAO.updatePassword(usr.getUsuario().getEmail(), novaSenha);
    						usr.getUsuario().setSenha(novaSenha);
    						helper.loading();
    						break;
    					case 0: // Deslogar
    						System.out.println("Saindo...");
    						helper.loading();
    						logado = false;
    					
    					default:
    						System.out.println("Opção desconhecida.");
    						break;
    					}
    					
    				} else if (usr.getCodigo() == 1) {
    					// Professor
    					System.out.println("1 - Listar treinamentos");
    					System.out.println("4 - Listar um aluno");
    					System.out.println("5 - Listar todos os alunos");
    					System.out.println("6 - Cadastrar aluno");
    					System.out.println("7 - Alterar cadastro de um aluno");
    					System.out.println("8 - Deletar aluno");
    					System.out.println("9 - Alterar sua senha");
    					System.out.println("0 - Sair");
    					System.out.print("Opção: ");
    					int opcao = scanner.nextInt();
    					scanner.nextLine();
    					
    					switch (opcao) {
    					case 1:
    						helper.cleanConsole();
    						System.out.println("Listar treinamentos:");
    						
    						 List<Treinamento> treinamentos = treinamentoDAO.listarTreinamentos();
    						 
    						 if (treinamentos.isEmpty()) {
     					        System.out.println("Nenhum aluno cadastrado.");
     					    } else {
     					        // Títulos das colunas
     					        System.out.printf(AMARELO + "%-30s | %-30s | %-30s | %-30s | %-15s%n ", "COD", "Nome", "Pontos", "Pontos Minimos" +  "Prof resp" + RESET);
     					        System.out.println("---------------------------------------------------------------------------------------------");
     					        
     					        for (Treinamento treinamento : treinamentos) {
     					            // Exibe as informações de cada aluno com formatação
     					            System.out.printf("%-30s | %-30s | %-30s | %-30s | %-15s%n", 
     					                treinamento.getId(), 
     					                treinamento.getNome(),
     					                treinamento.getPontos(),
     					                treinamento.getPontosMinimo(),
     					                treinamento.getProfessor()); 
     					        }
     					    }
    						
    						System.out.println("---------------------------------------------------------------------------------------------");
    					    System.out.println(AMARELO + "Pressione Enter para voltar ao menu..." + RESET);
    					    scanner.nextLine(); // Lê a entrada do usuário
    					case 4:
    						helper.cleanConsole();
    						System.out.println("Listar aluno:");
    						
    						System.out.print("Digite o email do aluno: ");
					        String emailListarAluno = scanner.nextLine();
					        
					        Aluno alunoListado = alunoDAO.buscarAlunoPorEmail(emailListarAluno);
					        if (alunoListado == null) {
					        	System.out.println("Nenhum aluno cadastrado.");
	    					    System.out.println("---------------------------------------------------------------------------------------------");
	    					    System.out.println(AMARELO + "Pressione Enter para voltar ao menu..." + RESET);
	    					    scanner.nextLine(); // Lê a entrada do usuário
					        	break;
					        }
					        System.out.printf(AMARELO + "%-30s | %-30s | %-15s | %-10s%n", "Nome", "Email", "Data de Nascimento", "Pontos" + RESET);
					        System.out.println(alunoListado.toString());
    						
    					    System.out.println("---------------------------------------------------------------------------------------------");
    					    System.out.println(AMARELO + "Pressione Enter para voltar ao menu..." + RESET);
    					    scanner.nextLine(); // Lê a entrada do usuário
    					case 5:
    						helper.cleanConsole();
    					    System.out.println("Listar alunos:");
    					    List<Aluno> alunos = alunoDAO.listarAlunos(); // Atribuindo a lista retornada à variável alunos

    					    if (alunos.isEmpty()) {
    					        System.out.println("Nenhum aluno cadastrado.");
    					    } else {
    					        // Títulos das colunas
    					        System.out.printf(AMARELO + "%-30s | %-30s | %-15s | %-10s%n", "Nome", "Email", "Data de Nascimento", "Pontos" + RESET);
    					        System.out.println("---------------------------------------------------------------------------------------------");
    					        
    					        for (Aluno aluno : alunos) {
    					            // Exibe as informações de cada aluno com formatação
    					            System.out.printf("%-30s | %-30s | %-15s | %-10d%n", 
    					                aluno.getNome(), 
    					                aluno.getEmail(), 
    					                aluno.getDataNasc(), 
    					                aluno.getPontos());
    					        }
    					    }

    					    System.out.println("---------------------------------------------------------------------------------------------");
    					    System.out.println(AMARELO + "Pressione Enter para voltar ao menu..." + RESET);
    					    scanner.nextLine(); // Lê a entrada do usuário
    					    break;
    					case 6:
    						helper.cleanConsole();
    						System.out.println("Cadastrar aluno");
    						
    						System.out.print("Nome: ");
					        String nomeAlunoCadastrar = scanner.nextLine();
					        
					        System.out.print("Email: ");
					        String emailAlunoCadastrar = scanner.nextLine();
					        
					        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
					        String dataNascStr = scanner.nextLine();
					        LocalDate dataNascAlunoCadastrar = LocalDate.parse(dataNascStr);
					        
					        Aluno alnCadastrar = new Aluno(nomeAlunoCadastrar, emailAlunoCadastrar, dataNascAlunoCadastrar);
					        alunoDAO.create(alnCadastrar);
					        helper.loading();
					        break;
    					
    					case 7:
    						helper.cleanConsole();
    						System.out.println("Alterar cadastro de um aluno");
    						
    						System.out.print("Digite o email do aluno: ");
					        String emailAlunoAtualizar = scanner.nextLine();
					        
					        Aluno alnAtualizar = alunoDAO.buscarAlunoPorEmail(emailAlunoAtualizar);

					        if (alnAtualizar == null) {
					        	System.out.println("Nenhum aluno cadastrado.");
					        	break;
					        }
					        helper.cleanConsole();
					        
					        String oldMail = alnAtualizar.getEmail();
					     // Atualizar Nome
					        System.out.println("Nome atual: " + AMARELO + alnAtualizar.getNome() + RESET);
					        boolean atualizarNome = helper.opcao("Alterar nome? (S/N): ");
					        if (atualizarNome) {
					        	System.out.print("Nome: ");
					        	String novoNome = scanner.nextLine();
					        	alnAtualizar.setNome(novoNome);
					        }
					        
					        // Atualizar Email
					        System.out.println("Email atual: " + AMARELO + alnAtualizar.getEmail()  + RESET);
					        boolean atualizarEmail = helper.opcao("Alterar email? (S/N): ");
					        if (atualizarEmail) {
					        	
					        	System.out.print("Email: ");
					        	String novoEmail = scanner.nextLine();
					        	alnAtualizar.setEmail(novoEmail);
					        }
					        
					        // Atualizar Data de Nascimento
					        System.out.println("Data de nascimento atual: " + AMARELO + alnAtualizar.getDataNasc() + RESET);
					        boolean atualizarNascimento = helper.opcao("Alterar data de nascimento? (S/N): ");
					        if (atualizarNascimento) {
					        	 System.out.print("Data de Nascimento (YYYY-MM-DD): ");
							     String atualizarDataNasc = scanner.nextLine();
							     try {
							    	 LocalDate dataNascAlunoAtualizar = LocalDate.parse(atualizarDataNasc);
							            alnAtualizar.setDataNasc(dataNascAlunoAtualizar); 
							        } catch (Exception e) {
							            System.out.println("Data inválida. A data não foi alterada.");
							        }
					        }
					        
						    // Atualizar Senha
					        System.out.println("Senha atual: " + AMARELO + alnAtualizar.getSenha() + RESET);
					        boolean atualizarSenha = helper.opcao("Alterar senha? (S/N): ");
					        if (atualizarSenha) {
					        	System.out.print("Email: ");
					        	String novaSenha = scanner.nextLine();
					        	alnAtualizar.setSenha(novaSenha);
					        }
					        
					        System.out.println(alnAtualizar.toString());
					        alunoDAO.atualizarAluno(oldMail, alnAtualizar);
					        
    						break;
    						
    					case 8:
    						helper.cleanConsole();
    						System.out.println("Deletar um aluno");
    						
    						System.out.print("Digite o email do aluno: ");
					        String emailDeletarAluno = scanner.nextLine();
					        
					        Aluno buscarAlunoDeletar = alunoDAO.buscarAlunoPorEmail(emailDeletarAluno);
					        
					        if (buscarAlunoDeletar == null) {
					        	System.out.println("Nenhum aluno cadastrado.");
					        	helper.loading();
					        	break;
					        }
					        
					        alunoDAO.deletarAluno(emailDeletarAluno);
					        break;
    					case 9: // Alterar senha
    						helper.cleanConsole();
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
    					case 0: // Deslogar
    						System.out.println("Saindo...");
    						helper.loading();
    						logado = false;
    					default:
    						System.out.println("Opção desconhecida.");
    						break;
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