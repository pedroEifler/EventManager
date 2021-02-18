package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConexao() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/mercado","root", "1234");
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
		
		
	}
}
