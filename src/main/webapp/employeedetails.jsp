<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="net.javaguides.employeemanagement.model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f4f4f4;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Employee Details</h1>
    <table>
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
                <th>Address</th>
                <th>Contact</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Employee> employeeList = (List<Employee>) request.getAttribute("emplist");
                if (employeeList != null) {
                    for (Employee employee : employeeList) {
            %>
                        <tr>
                            <td><%= employee.getFirstName() %></td>
                            <td><%= employee.getLastName() %></td>
                            <td><%= employee.getUsername() %></td>
                            <td><%= employee.getAddress() %></td>
                            <td><%= employee.getContact() %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="5">No employees found.</td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
	<h3>
		<a href="employeeregister.jsp">Add Employee</a>
	</h3>
</body>
</html>
