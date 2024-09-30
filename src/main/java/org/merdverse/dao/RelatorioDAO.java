package org.merdverse.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.merdverse.models.Aluno;
import org.merdverse.models.Relatorio;
import org.merdverse.models.Sessao;

public class RelatorioDAO {
	public void create(Relatorio relatorio) {
	    String INSERT_RELATORIO = "INSERT INTO relatorio (cod_relatorio, aluno_email, cod_treinamento, data_relatorio, pontos, aprovado) VALUES (?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = ConexaoDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(INSERT_RELATORIO)) {

	        ps.setString(1, relatorio.getCod_relatorio());
	        ps.setString(2, relatorio.getAluno_email());
	        ps.setString(3, relatorio.getCod_treinamento());
	        
	        // Pega a data atual e converte para java.sql.Date
	        LocalDate localDate = LocalDate.now();
	        Date dataAtual = Date.valueOf(localDate);
	        ps.setDate(4, dataAtual);
	        
	        ps.setInt(5, relatorio.getPontos());
	        ps.setInt(6, relatorio.getAprovado());

	        // Executa o INSERT
	        ps.executeUpdate();
	        

	        System.out.println("Relatório Criado com sucesso.");
	    } catch (SQLException e) {
	        System.out.println("Erro ao criar relatório: " + e.getErrorCode() + " - " + e.getMessage());
	    }
	}
	
	public List<Relatorio> listarRelatorios(String aluno_email) {
	    String SELECT_ALL_RELATORIOS_BY_USER = "SELECT * FROM relatorio WHERE aluno_email = ?";
	    List<Relatorio> relatorios = new ArrayList<>();
	    
	    try (Connection conn = ConexaoDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(SELECT_ALL_RELATORIOS_BY_USER)) {

	        ps.setString(1, aluno_email);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            String cod_relatorio = rs.getString("cod_relatorio");
	            String aluno_email1 = rs.getString("aluno_email");
	            String cod_treinamento = rs.getString("cod_treinamento");
	            Date data_relatorioSQL = rs.getDate("data_relatorio");
	            LocalDate data_relatorio = data_relatorioSQL.toLocalDate();
	            int pontos = rs.getInt("pontos");
	            int aprovado = rs.getInt("aprovado");

	            Relatorio relatorio = new Relatorio(cod_relatorio, aluno_email1, cod_treinamento, data_relatorio, pontos, aprovado);
	            relatorios.add(relatorio);
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao listar relatórios: " + e.getMessage());
	    }
	    
	    return relatorios;
	} 
}
