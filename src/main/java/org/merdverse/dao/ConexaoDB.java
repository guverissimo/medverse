package org.merdverse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    // Definindo as informações de conexão com o banco de dados Oracle
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
//    private static final String USER = System.getenv("DB_USER"); 
//    private static final String PASSWORD = System.getenv("DB_PASSWORD"); 

    // Registrar o driver JDBC do Oracle
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do Oracle não encontrado: " + e.getMessage());
        }
    }

    // Método para obter uma conexão com o banco de dados Oracle
    public static Connection getConnection() {
        Connection conexao = null;
        try {
//            if (USER == null || PASSWORD == null) {
//                throw new SQLException("Usuário ou senha do banco de dados não configurados.");
//            }
            conexao = DriverManager.getConnection(URL, "RM551244", "130203");
//            System.out.println("Conexão com o banco de dados Oracle estabelecida.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conexao;
    }

    // Método para fechar a conexão  
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o banco de dados Oracle fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão com o banco de dados Oracle: " + e.getMessage());
            }
        }
    }
}
