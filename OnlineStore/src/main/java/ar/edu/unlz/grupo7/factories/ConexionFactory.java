package ar.edu.unlz.grupo7.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionFactory {

	private static final String SERVER = "jdbc:mysql://localhost";
	private static final String BASE = "DBGrupo7";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection getConexion() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		var connectionString = SERVER + "/" + BASE;
		System.out.println(connectionString);
		return DriverManager.getConnection(connectionString, USER, PASSWORD);
	}

}
