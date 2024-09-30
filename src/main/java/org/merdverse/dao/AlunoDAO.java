package org.merdverse.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.merdverse.models.Aluno;

public class AlunoDAO {
    private static final String UPDATE_ALUNO = "UPDATE aluno SET nome_aluno = ?, email_aluno = ?, senha_aluno = ?, data_nasc_aluno = ?, pontos = ? WHERE email_aluno = ?";
    private static final String DELETE_ALUNO = "DELETE FROM aluno WHERE email_aluno = ?";
    
    public void create(Aluno aluno) {
    	String INSERT_ALUNO = "INSERT INTO aluno (nome_aluno, email_aluno, pontos, data_nasc_aluno, senha_aluno) VALUES (?, ?, ?, ?, ?)";
    	
    	try(Connection conn = ConexaoDB.getConnection();
    		PreparedStatement ps = conn.prepareStatement(INSERT_ALUNO)) 
    	{
    		ps.setString(1, aluno.getNome());
    		ps.setString(2, aluno.getEmail());
    		ps.setInt(3, aluno.getPontos());
    		LocalDate dataNascimento = aluno.getDataNasc();
    		ps.setDate(4, java.sql.Date.valueOf(dataNascimento));
    		ps.setString(5, aluno.getSenha());
    		ps.executeUpdate();
    		
    		System.out.println("Aluno cadastrado: " + aluno.getNome());
    		
    	} catch (SQLException e) {
    		System.out.println("Cannot create Aluno: " + e.getMessage());
    	}
    	
    }
    
    public void updatePassword(String email, String senha) {
        String UPDATE_SENHA_ALUNO = "UPDATE aluno SET senha_aluno = ? WHERE email_aluno = ?";

        // Obtendo a conexão
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SENHA_ALUNO)) {

            stmt.setString(1, senha);
            stmt.setString(2, email);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Aluno buscarAlunoPorEmail(String emailAluno) {
        Aluno aluno = null;
        String SELECT_ALUNO_BY_EMAIL = "SELECT * FROM aluno WHERE email_aluno = ?"; // Certifique-se de que esta string está correta.

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALUNO_BY_EMAIL)) {

            ps.setString(1, emailAluno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome_aluno");
                int pontos = rs.getInt("pontos");
                Date dataNascSQL = rs.getDate("data_nasc_aluno");
                LocalDate dataNasc = dataNascSQL.toLocalDate();
                aluno = new Aluno(nome, emailAluno, dataNasc); // Passando o email encontrado
                aluno.setPontos(pontos);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return aluno; // Retorna o aluno encontrado ou null
    }
    
    public List<Aluno> listarAlunos() {
    	String SELECT_ALL_ALUNOS = "SELECT * FROM aluno";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SELECT_ALL_ALUNOS);

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int pontos = rs.getInt("pontos");
                Date dataNascSQL = rs.getDate("data_nasc");
                LocalDate dataNasc = dataNascSQL.toLocalDate();
                Aluno aluno = new Aluno(nome, email, dataNasc);
                aluno.setPontos(pontos);
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }
    
    public void atualizarAluno(String email, Aluno aluno) {
        // Obtendo a conexão
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_ALUNO)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getSenha());
            stmt.setDate(4, Date.valueOf(aluno.getDataNasc())); // Certifique-se de que a data está no formato correto
            stmt.setInt(5, aluno.getPontos());
            stmt.setString(6, email); // Use o email aqui para identificar qual aluno atualizar

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletarAluno(String email) {
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALUNO)) {

            ps.setString(1, email);
            ps.executeUpdate();

            System.out.println("Aluno deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
        }
    }

}
