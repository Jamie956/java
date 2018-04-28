package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.rowmapper.RowMapper;

public class JdbcUtils {
	private static final String URL = "jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&useSSL=false";
	private static final String NAME = "root";
	private static final String PASSWORD = "123456";
	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";

	static {
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Connection to mysql
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Close all
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
		close(rs);
		close(stmt);
		close(conn);
	}
	
	/**
	 * Close ResultSet
	 * 
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close Statement
	 * 
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close Connection
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Query
	 * @param sql
	 * @param rowMapper
	 * @return
	 */
	public static <T> List<T> query(String sql, RowMapper<T> rowMapper) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<T> result = new ArrayList<T>();
			while (rs.next()) {
				T t = rowMapper.getEntity(rs);
				result.add(t);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	
}
