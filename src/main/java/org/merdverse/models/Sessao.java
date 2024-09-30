package org.merdverse.models;

public class Sessao {
	private int id;
	private Aluno aluno;
	private Treinamento treinamento;
	private int pontos;
	
	public Sessao(Aluno aluno, Treinamento treinamento) {
		this.aluno = aluno;
		this.treinamento = treinamento;
		
		System.out.println("Sessão de treinamento iniciada.");
		
		// Gerando um numero aleátorio para simulação
		int pontosAleatorio = (int) (Math.random() * treinamento.getPontosMinimo() + 1 );
        setPontos(pontosAleatorio);
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
}


