package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.merdverse.models.Sessao;

public class SessaoDAO {
	public void create(Sessao sessao) {
		String INSERT_SESSAO = "INSERT INTO sessao (cod_sessao, aluno_email, cod_treinamento, set ativo) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = ConexaoDB.getConnection();
	             PreparedStatement ps = conn.prepareStatement(INSERT_SESSAO)) {
				ps.setString(1, sessao.getId());
				ps.setString(2, sessao.getAluno_email());
				ps.setString(3, sessao.getTreinamento().getId());
				ps.setInt(4, 0);
				ps.setInt(5,sessao.isAtivo());
			
		} catch (SQLException e ) {
			System.out.println("Erro ao criar sessao: " + e.getMessage());
		}
	}
	
	public void atualizarAtivoSessao(Sessao sessao) {
		String UPDATE_ATIVO_SESSAO = "UPDATE sessao SET ativo = ? WHERE cod_sessao = ?";
		try (Connection conn = ConexaoDB.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(UPDATE_ATIVO_SESSAO)) {
				stmt.setInt(1, sessao.isAtivo());
	            stmt.setString(2, sessao.getId());  // Usando o ID para identificar o aluno a ser atualizado
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
}
