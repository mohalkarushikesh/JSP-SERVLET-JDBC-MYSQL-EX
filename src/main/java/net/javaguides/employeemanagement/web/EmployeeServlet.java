package net.javaguides.employeemanagement.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.employeemanagement.dao.EmployeeDao;
import net.javaguides.employeemanagement.model.Employee;

@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao empdao;

	public void init() {
		empdao = new EmployeeDao();
	}

	// do post method for registration and fetching employees
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Collect form data
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");

		// Create a new Employee object
		Employee emp = new Employee();
		emp.setFirstName(firstname);
		emp.setLastName(lastname);
		emp.setUsername(username);
		emp.setPassword(password);
		emp.setAddress(address);
		emp.setContact(contact);

		try {
			// Register the employee into the database
			empdao.registerEmployee(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// List to store fetched employees
		List<Employee> emplist = new ArrayList<>();
		String query = "SELECT * FROM employee;";

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false",
				"root", "root"); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {

			// Process the result set and populate the employee list
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setUsername(rs.getString("username"));
				employee.setAddress(rs.getString("address"));
				employee.setContact(rs.getString("contact"));
				emplist.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Set the employee list as a request attribute
		request.setAttribute("emplist", emplist);

		// Forward the request to the employeedetails.jsp page
		employedetails(request, response);
	}

	// Method to forward the request to the employee details JSP page
	private void employedetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("employeedetails.jsp");
		dispatcher.forward(request, response); // Forward to employeedetails.jsp
	}
}
