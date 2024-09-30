package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.merdverse.models.Relatorio;
import org.merdverse.models.Sessao;

public class RelatorioDAO {
	public void create(Relatorio relatorio) {
		String INSERT_SESSAO = "INSERT INTO relatorio (cod_relatorio, aluno_email, cod_treinamento, data_relatorio, pontos, aprovado) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = ConexaoDB.getConnection();
	             PreparedStatement ps = conn.prepareStatement(INSERT_SESSAO)) {
				ps.setString(1, relatorio.getCod_relatorio());
				ps.setString(2, relatorio.getAluno_email());
				ps.setString(3, relatorio.getCod_treinamento());
				java.sql.Date dataAtual = new java.sql.Date(System.currentTimeMillis());
				ps.setDate(4, dataAtual);
				ps.setInt(5, relatorio.getPontos());
				ps.setInt(6, relatorio.getAprovado());
		} catch (SQLException e ) {
			System.out.println("Erro ao criar sessao: " + e.getMessage());
		}
	}
}
