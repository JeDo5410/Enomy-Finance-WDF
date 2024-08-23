package com.user;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserServlet extends javax.servlet.http.HttpServlet{
	private static final long serialVersionUID = 1L;
	private final UserDAO userDAO = new UserDAO();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName = request.getParameter("full_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		user user = new user();
		user.setFullName(fullName);
		user.setEmail (email);
		user.setPassword(password);
		
	
		try {userDAO.insertUser (user);
		RequestDispatcher rd = request.getRequestDispatcher("thankyou");
		rd.forward(request, response);
		}
		catch (SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        
		}
	}
}
