package org.merdverse.models;

import java.util.Date;

import org.merdverse.helper.Helper;

public class Relatorio {
	private String cod_relatorio;
	private String aluno_email;
	private String cod_treinamento;
	private Date   data_relatorio;
	private int	   pontos;
	private int    aprovado;
	
	Helper hp = new Helper();
	public Relatorio(String aluno_email, String cod_treinamento, int pontos,
		int aprovado) {
		this.cod_relatorio = hp.gerarString();
		this.aluno_email = aluno_email;
		this.cod_treinamento = cod_treinamento;
		this.pontos = pontos;
		this.aprovado = aprovado;
	}

	public String getCod_relatorio() {
		return cod_relatorio;
	}
	public void setCod_relatorio(String cod_relatorio) {
		this.cod_relatorio = cod_relatorio;
	}
	public String getAluno_email() {
		return aluno_email;
	}
	public void setAluno_email(String aluno_email) {
		this.aluno_email = aluno_email;
	}
	public String getCod_treinamento() {
		return cod_treinamento;
	}
	public void setCod_treinamento(String cod_treinamento) {
		this.cod_treinamento = cod_treinamento;
	}
	public Date getData_relatorio() {
		return data_relatorio;
	}
	public void setData_relatorio(Date data_relatorio) {
		this.data_relatorio = data_relatorio;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public int getAprovado() {
		return aprovado;
	}
	public void setAprovado(int aprovado) {
		this.aprovado = aprovado;
	}
	
}
