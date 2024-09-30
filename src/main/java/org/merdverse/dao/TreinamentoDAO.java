package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.merdverse.models.Treinamento;

public class TreinamentoDAO {

	public void create(Treinamento treinamento) {
		String INSET_TREINAMENTO = "INSERT  INTO treinamento (cod_treinamento, nome_treinamento, pontos_treinamento, pontos_minimo, email_professor) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = ConexaoDB.getConnection();
				PreparedStatement ps = conn.prepareStatement(INSET_TREINAMENTO)) {
			
			ps.setString(1, treinamento.getId());
			ps.setString(2, treinamento.getNome());
			ps.setInt(3, treinamento.getPontos());
			ps.setInt(4, treinamento.getPontosMinimo());
			ps.setString(5, treinamento.getProfessor());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro ao criar treinamento: " + e.getMessage());
		}
	}
	
	public List<Treinamento> listarTreinamentos() {
		String SELECT_ALL_TREINAMENTOS = "SELECT * FROM treinamento";
        List<Treinamento> treinamentos = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SELECT_ALL_TREINAMENTOS);

            while (rs.next()) {
            	String id = rs.getString("cod_treinamento");
                String nome = rs.getString("nome_treinamento");
                int pontos = rs.getInt("pontos");
                int pontos_minimo = rs.getInt("pontos_minimo");
                String email_professor = rs.getString("email_professor");
                Treinamento treinamento = new Treinamento(id, nome, pontos, pontos_minimo, email_professor);
                treinamentos.add(treinamento);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar treinamento: " + e.getMessage());
        }
        return treinamentos;
    }
	
	
}
