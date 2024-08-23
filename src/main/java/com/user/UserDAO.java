package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private static final String INSERT_USER_QUERY = "INSERT INTO user (fullname, email, password) VALUES (?, ?, ?);";
	private static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=? LIMIT 1;";
	
	public void insertUser (user user) throws SQLException {
		try (Connection connection = DatabaseUtils.getConnection();) {
			try (PreparedStatement insertPreparedStatement = connection.prepareStatement(INSERT_USER_QUERY)) {
				insertPreparedStatement.setString(1, user.getFullName());
				insertPreparedStatement.setString(2, user.getEmail());
				insertPreparedStatement.setString(3, user.getPassword());
				
				insertPreparedStatement.executeUpdate();
			}
		}		
	}
	
	public user getUserByEmail(String email) {
		user user = null;
		try (Connection connection = DatabaseUtils.getConnection();) {
			try (PreparedStatement selectPreparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_QUERY)) {
				selectPreparedStatement.setString(1, email);
				
				ResultSet resultSet = selectPreparedStatement.executeQuery();
				
				if (resultSet.next()) {
					user = new user();
					user.setFullName(resultSet.getString("fullname"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}