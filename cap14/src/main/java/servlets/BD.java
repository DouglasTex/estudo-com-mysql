package servlets;

import java.sql.*;

public class BD {
	
	public Connection connection = null;
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String DBNAME = "atrativos";
	private final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	private final String LOGIN = "root";
	private final String SENHA = "password";
	
	public boolean getConnection() {
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, LOGIN, SENHA);
			System.out.println("Conectou");
			return true;
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver não encontrado " + erro.toString());
			return false;
		} catch (SQLException erro) {
			System.out.println("Falha ao conectar "+ erro.toString());
			return false;
		}
		
	}
	
	public void close() {
		try {
			connection.close();
			System.out.println("Desconectou");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
