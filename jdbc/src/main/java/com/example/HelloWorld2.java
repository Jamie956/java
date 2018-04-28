package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloWorld2 {
	static final String URL = "jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&useSSL=false";
	static final String NAME = "root";
	static final String PASSWORD = "123456";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM user";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString("id"));
			System.out.println(rs.getString("name"));
			System.out.println(rs.getString("email"));
		}
		
		rs.close();
		stmt.close();
		conn.close();
	}

}

