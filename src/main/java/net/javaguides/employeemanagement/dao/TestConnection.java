package net.javaguides.employeemanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

	public static void main(String args[]) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		if (con != null) {
			System.out.println("MYSQL Connection Established");

		} else {
			System.out.println("MYSQL Connection Not-Established");
		}

	}

}
