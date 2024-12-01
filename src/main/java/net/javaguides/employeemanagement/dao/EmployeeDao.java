package net.javaguides.employeemanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.javaguides.employeemanagement.model.Employee;

public class EmployeeDao {

	public int registerEmployee(Employee employee) throws ClassNotFoundException {
		// insert query
		String INSERT_USERS_SQL = "INSERT INTO employee (first_name, last_name, username, password, address, contact) VALUES(?,?,?,?,?,?);";
		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", // connection create
				"root", "root"); 
			PreparedStatement psmt = con.prepareStatement(INSERT_USERS_SQL)) { // prepared stmt object to send query
			psmt.setString(1, employee.getFirstName()); // get the values and set them to column
			psmt.setString(2, employee.getLastName());
			psmt.setString(3, employee.getUsername());
			psmt.setString(4, employee.getPassword());
			psmt.setString(5, employee.getAddress());
			psmt.setString(6, employee.getContact());

			/* System.out.println(psmt); */
			result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// create method print sql exception
	private void printSQLException(SQLException ex) {
		for(Throwable e:ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: "+((SQLException)e).getSQLState());
				System.err.println("Error Code:"+((SQLException)e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while(t!=null) {
					System.out.println("Cause: "+t);
					t = t.getCause();
				}
			}
		}
	}
	
	
}
