/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fernado
 */
public class Conexao {
    private String driver = "org.postgresql.Driver";
    private String URL = "jdbc:postgresql://localhost:5432/sigind";
    private String USER = "postgres";
    private String SENHA = "postgres";
    private Connection conn;
 
    public Conexao() {
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(URL, USER, SENHA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public Connection getConn() {
        return conn;
    }
 
    public void fecharConexao() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
