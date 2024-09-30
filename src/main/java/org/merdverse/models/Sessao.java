package org.merdverse.models;

import org.merdverse.dao.RelatorioDAO;
import org.merdverse.dao.SessaoDAO;
import org.merdverse.helper.Helper;

public class Sessao {
	private String id;
	private String aluno_email;
	private Treinamento treinamento;
	private int pontos;
	private int ativo;
	private int aprovado;
	
	public Sessao(String aluno_email, Treinamento treinamento) {
		this.aluno_email = aluno_email;
		this.treinamento = treinamento;
		aprovado = 0;
	}
	
	Helper hp = new Helper();
	SessaoDAO sessaoDAO = new SessaoDAO();
	RelatorioDAO relatorioDAO = new RelatorioDAO();
	public String getAluno_email() {
		return aluno_email;
	}
	public void setAluno_email(String aluno_email) {
		this.aluno_email = aluno_email;
	}
	public Treinamento getTreinamento() {
		return treinamento;
	}
	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
	}
	public int isAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getAprovado() {
		return aprovado;
	}

	public void setAprovado(int aprovado) {
		this.aprovado = aprovado;
	}
	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public void iniciarSim(Sessao sessao) {
		this.ativo = 1;
		System.out.println("Sessão de treinamento iniciada.");
		sessaoDAO.atualizarAtivoSessao(sessao);
		sessaoDAO.create(sessao);
		// Gerando um numero aleátorio para simulação
		int pontosAleatorio = (int) (Math.random() * treinamento.getPontosMinimo() + 1 );
        setPontos(pontosAleatorio);
        
       checkPoint(pontosAleatorio, sessao);
	}
	
	public void checkPoint(int pontos, Sessao sessao) {
		System.out.println("Contando seus pontos....");
		hp.loading();
		System.out.println("Sua pontuação foi de: " + pontos);
		if (pontos >= treinamento.getPontosMinimo()) {
			System.out.println("Aprovado");
			setAtivo(0);
			setAprovado(1);
			sessaoDAO.atualizarAtivoSessao(sessao);
			
		} else {
			System.out.println("Você não atingiu a pontuação necessária para ser aprovado!");
			System.out.println("Reiniciando sessão...");
			setAtivo(0);
			setAprovado(0);
			hp.loading();
		}
	}
	
}


